#!/usr/bin/env python3
"""Install ONLY Agile + Java Crash Course pages in Commercial IT Bootcamp.
Uses fixed Thymeleaf wrappers and explicit controller template returns.
No changes to base.html, topic.html, existing tool lessons, CSS or security.
"""
from pathlib import Path
import sys

root = Path.cwd()
source = Path(__file__).resolve().parent
controller = root / 'src/main/java/com/commercialit/bootcamp/controller/InternshipController.java'
templates = root / 'src/main/resources/templates/internships'
if not (root/'pom.xml').is_file() or not controller.is_file() or not templates.is_dir():
    sys.exit('ERROR: Run from commercial-it-bootcamp project root; expected files missing. No changes made.')

slugs = ['agile-scrum-kanban', 'java-crash-course', 'java-data-types', 'java-generics',
         'java-logical-decisions', 'java-loops', 'java-math', 'java-dates', 'java-functional-programming']
files = [name+'.html' for name in slugs]+['lesson-'+name+'.html' for name in slugs]
for name in files:
    file = source/name
    if not file.is_file():
        sys.exit('ERROR: Missing installer companion '+name+'. No changes made.')
    dest = templates/name
    if dest.exists() and dest.read_bytes() != file.read_bytes():
        sys.exit('ERROR: '+str(dest)+' already exists with different content. No changes made.')
for slug in slugs:
    if 'th:fragment="lesson"' not in (source/(slug+'.html')).read_text():
        sys.exit('ERROR: Missing lesson fragment: '+slug+'. No changes made.')
    fixed = (source/('lesson-'+slug+'.html')).read_text()
    if f'internships/{slug} :: lesson' not in fixed or fixed.count('th:replace=') != 2:
        sys.exit('ERROR: Invalid dedicated wrapper for '+slug+'. No changes made.')

java = controller.read_text()
if 'package com.commercialit.bootcamp.controller;' not in java:
    sys.exit('ERROR: Unexpected controller package. No changes made.')
section_anchor = 'return "internships/section";'
topic_anchor = 'return "internships/topic";'
if java.count(section_anchor) != 1 or java.count(topic_anchor) != 1:
    sys.exit('ERROR: Expected one existing section/topic return. No changes made.')
if '@GetMapping("/internships/{section}")' not in java or '@GetMapping("/internships/{section}/{topic}")' not in java:
    sys.exit('ERROR: Unexpected controller mappings. No changes made.')
for name in ('agile-scrum-kanban', 'java-crash-course'):
    if f'SECTIONS.put("{name}"' not in java:
        sys.exit('ERROR: Existing curriculum does not define section '+name+'. No changes made.')
for slug in slugs[2:]:
    topic = slug.removeprefix('java-')
    if f'Map.entry("{topic}"' not in java:
        sys.exit('ERROR: Existing Java submenu missing '+topic+'. No changes made.')

section_lines = [f'        if ("{name}".equals(section)) return "internships/lesson-{name}";' for name in slugs[:2]]
topic_lines = [f'        if ("java-crash-course".equals(section) && "{slug.removeprefix("java-")}".equals(topic)) return "internships/lesson-{slug}";' for slug in slugs[2:]]
updated=java
for line in section_lines:
    if line.strip() not in updated:
        updated=updated.replace(section_anchor,line+'\n        '+section_anchor,1)
for line in topic_lines:
    if line.strip() not in updated:
        updated=updated.replace(topic_anchor,line+'\n        '+topic_anchor,1)
for line in section_lines + topic_lines:
    if updated.count(line.strip()) != 1:
        sys.exit('ERROR: Route validation failed for '+line+'. No changes made.')

# Re-run safety: ensure only fixed explicit mappings are added; no existing return removed.
if updated.count(section_anchor) != 1 or updated.count(topic_anchor) != 1:
    sys.exit('ERROR: Unexpected controller changes. No changes made.')
if updated != java:
    backup = controller.with_suffix('.java.before-agile-java.bak')
    if not backup.exists():
        backup.write_text(java)
    controller.write_text(updated)
for name in files:
    dest=templates/name
    if not dest.exists():
        dest.write_bytes((source/name).read_bytes())
print('SUCCESS: Added Agile/Scrum/Kanban page and Java Crash Course overview + seven lessons.')
print('Section routes: /internships/agile-scrum-kanban and /internships/java-crash-course')
print('Topic routes: /internships/java-crash-course/{data-types,generics,logical-decisions,loops,math,dates,functional-programming}')
print('Left unchanged: existing tools lessons, base.html, topic.html, SecurityConfig.java and bootcamp.css.')
