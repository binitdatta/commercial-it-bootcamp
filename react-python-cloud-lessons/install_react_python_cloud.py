#!/usr/bin/env python3
"""Fail-closed installation: only 6 new lesson templates, 6 wrappers, 5 route branches."""
from pathlib import Path
import sys, shutil, re
r=Path.cwd();src=Path(__file__).resolve().parent
controller=r/'src/main/java/com/commercialit/bootcamp/controller/InternshipController.java'
tpl=r/'src/main/resources/templates/internships'
sections={'react-crash-course':'lesson-react-crash-course','python-3-crash-course':'lesson-python-3-crash-course','cloud-computing':'lesson-cloud-computing'}
topics={'aws':'lesson-aws','google-cloud':'lesson-google-cloud','azure':'lesson-azure'}
if not (r/'pom.xml').is_file() or not controller.is_file() or not tpl.is_dir():
    sys.exit('ERROR: Start from project root containing pom.xml; no changes made.')
names=[f'{n}.html' for n in sections|topics]+[f'{n}.html' for n in list(sections.values())+list(topics.values())]
for name in names:
    a=src/name;b=tpl/name
    if not a.is_file() or (b.exists() and b.read_bytes()!=a.read_bytes()):
        sys.exit(f'ERROR: Missing or conflicting lesson template: {name}. No changes made.')
for slug in sections|topics:
    if 'th:fragment="lesson"' not in (src/f'{slug}.html').read_text():
        sys.exit(f'ERROR: Missing lesson fragment for {slug}. No changes made.')
java=controller.read_text()
sig1='public String section(@PathVariable String section, Model model)'
sig2='public String topic(@PathVariable String section, @PathVariable String topic, Model model)'
if java.count(sig1)!=1 or java.count(sig2)!=1 or java.count('return "internships/section";')!=1 or java.count('return "internships/topic";')!=1:
    sys.exit('ERROR: Unrecognized controller structure; no changes made.')
if any(f'SECTIONS.put("{s}"' not in java for s in sections) or any(f'Map.entry("{s}"' not in java for s in topics):
    sys.exit('ERROR: Curriculum catalog slugs differ; no changes made.')
# Inspect the exact method bodies before patching. Do not silently override unrelated routes.
start1=java.index(sig1);start2=java.index(sig2)
if start1>=start2:sys.exit('ERROR: Controller methods are out of expected order; no changes.')
section_part=java[start1:start2]
topic_part=java[start2:]
for slug,wrapper in sections.items():
    stmt=f'if ("{slug}".equals(section)) return "internships/{wrapper}";'
    if section_part.count(stmt)>1 or (f'return "internships/{wrapper}";' in java and stmt not in section_part):
        sys.exit(f'ERROR: Conflicting route for {slug}. No changes made.')
    if stmt not in section_part:
        section_part=section_part.replace('return "internships/section";',stmt+'\n        return "internships/section";',1)
for slug,wrapper in topics.items():
    stmt=f'if ("cloud-computing".equals(section) && "{slug}".equals(topic)) return "internships/{wrapper}";'
    if topic_part.count(stmt)>1 or (f'return "internships/{wrapper}";' in java and stmt not in topic_part):
        sys.exit(f'ERROR: Conflicting route for {slug}. No changes made.')
    if stmt not in topic_part:
        topic_part=topic_part.replace('return "internships/topic";',stmt+'\n        return "internships/topic";',1)
new_java=java[:start1]+section_part+topic_part
if new_java!=java:
    backup=controller.with_suffix('.java.before-react-python-cloud.bak')
    if not backup.exists():shutil.copy2(controller,backup)
    controller.write_text(new_java)
for name in names:
    dst=tpl/name
    if not dst.exists():shutil.copy2(src/name,dst)
print('SUCCESS: React, Python, Cloud overview, AWS, Google Cloud and Azure lessons installed.')
print('UNCHANGED: topic.html, section.html, base.html, all existing lesson templates, CSS, JS, SecurityConfig.java.')
print('REBUILD: mvn clean package, restart application.')
