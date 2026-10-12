#!/usr/bin/env python3
"""Install a single exact-route Git training lesson. Fail closed on conflicts."""
from pathlib import Path
import re
import shutil
import sys

project = Path.cwd()
source = Path(__file__).resolve().parent
controller = project / 'src/main/java/com/commercialit/bootcamp/controller'
templates = project / 'src/main/resources/templates/internships'
items = [
    (source/'src/main/java/com/commercialit/bootcamp/controller/GitDeepDiveController.java', controller/'GitDeepDiveController.java'),
    (source/'src/main/resources/templates/internships/lesson-git-deep-dive.html', templates/'lesson-git-deep-dive.html'),
    (source/'src/main/resources/templates/internships/git-deep-dive-content.html', templates/'git-deep-dive-content.html'),
]
if not (project/'pom.xml').is_file() or not controller.is_dir() or not templates.is_dir():
    sys.exit('ERROR: Run installer from the commercial-it-bootcamp project root. No changes made.')
for src, dst in items:
    if not src.is_file():
        sys.exit(f'ERROR: Missing package file {src}. No changes made.')
    if dst.exists() and dst.read_bytes() != src.read_bytes():
        sys.exit(f'ERROR: Existing conflicting file {dst}. No changes made.')
# The existing dynamic /internships/{section} route can coexist with this literal route.
# Reject any competing explicit Git deep dive mapping in another Java controller.
for java in controller.glob('*.java'):
    if java.name == 'GitDeepDiveController.java':
        continue
    body = re.sub(r'/\*.*?\*/|//[^\n]*', '', java.read_text(), flags=re.S)
    if re.search(r'@(?:GetMapping|RequestMapping)\s*\(\s*"(?:/internships/)?git-deep-dive"', body):
        sys.exit(f'ERROR: Existing explicit Git deep dive route in {java.name}. No changes made.')
lesson = items[2][0].read_text()
wrapper = items[1][0].read_text()
if 'th:fragment="lesson"' not in lesson or 'internships/git-deep-dive-content :: lesson' not in wrapper:
    sys.exit('ERROR: Templates do not match the expected fragment contract. No changes made.')
for src, dst in items:
    if not dst.exists():
        shutil.copy2(src, dst)
        print('ADDED:', dst.relative_to(project))
    else:
        print('ALREADY PRESENT:', dst.relative_to(project))
print('SUCCESS: Only the Git Deep Dive exact route and its two new templates installed.')
print('Next: mvn clean package, restart Spring Boot, then open /internships/git-deep-dive')
