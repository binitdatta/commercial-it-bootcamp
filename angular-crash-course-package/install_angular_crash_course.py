#!/usr/bin/env python3
"""Add only the Angular Crash Course page; refuse unknown controller layouts."""
from pathlib import Path
import sys
root = Path.cwd()
source = Path(__file__).resolve().parent
controller = root / 'src/main/java/com/commercialit/bootcamp/controller/InternshipController.java'
templates = root / 'src/main/resources/templates/internships'
if not (root / 'pom.xml').is_file() or not controller.is_file() or not templates.is_dir():
    sys.exit('ERROR: Run from the Commercial IT Bootcamp project root. No changes made.')
files = ['angular-crash-course.html', 'lesson-angular-crash-course.html']
for name in files:
    src = source / name
    dst = templates / name
    if not src.is_file():
        sys.exit(f'ERROR: Missing {src}. No changes made.')
    if dst.exists() and dst.read_bytes() != src.read_bytes():
        sys.exit(f'ERROR: Existing file differs: {dst}. No changes made.')
if 'th:fragment="lesson"' not in (source / files[0]).read_text():
    sys.exit('ERROR: Lesson fragment missing. No changes made.')
if 'internships/angular-crash-course :: lesson' not in (source / files[1]).read_text():
    sys.exit('ERROR: Wrapper fragment invalid. No changes made.')
java = controller.read_text()
anchor = 'return "internships/section";'
route = 'if ("angular-crash-course".equals(section)) return "internships/lesson-angular-crash-course";'
if java.count(anchor) != 1 or '@GetMapping("/internships/{section}")' not in java or 'SECTIONS.put("angular-crash-course"' not in java:
    sys.exit('ERROR: Unexpected controller structure. No changes made.')
if java.count(route) > 1:
    sys.exit('ERROR: Duplicate Angular mapping found. No changes made.')
# Check for a conflicting dedicated controller mapping before writing.
if java.count('return "internships/lesson-angular-crash-course";') > (1 if route in java else 0):
    sys.exit('ERROR: Conflicting Angular mapping found. No changes made.')
if route not in java:
    java_new = java.replace(anchor, '        ' + route + '\n        ' + anchor, 1)
    backup = controller.with_suffix('.java.before-angular.bak')
    if not backup.exists():
        backup.write_text(java)
    controller.write_text(java_new)
for name in files:
    dst = templates / name
    if not dst.exists():
        dst.write_bytes((source / name).read_bytes())
print('SUCCESS: Angular Crash Course installed at /internships/angular-crash-course')
print('Unchanged: base.html, topic.html, existing lesson templates, SecurityConfig.java, CSS and JS.')
