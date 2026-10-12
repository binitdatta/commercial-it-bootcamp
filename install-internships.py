#!/usr/bin/env python3
"""Patch existing Thymeleaf base navbar, preserving its current content and backup."""
from pathlib import Path
import sys
base=Path('src/main/resources/templates/base.html')
if not base.exists():
    sys.exit('Run this from commercial-it-bootcamp project root; base.html missing')
s=base.read_text()
backup=base.with_suffix('.html.before-internships.bak')
if not backup.exists(): backup.write_text(s)
nav=Path('internships-nav-snippet.html').read_text()
if '>Internships</a>' not in s:
    needle='                <li class="nav-item"><a class="nav-link" th:href="@{/about}">About</a></li>'
    if needle not in s: sys.exit('Could not locate About nav item; base.html unchanged')
    s=s.replace(needle, nav+'\n'+needle, 1)
if 'th:href="@{/css/internships.css}"' not in s:
    needle='<link rel="stylesheet" th:href="@{/css/bootcamp.css}">'
    if needle not in s: sys.exit('Could not locate bootcamp CSS inclusion')
    s=s.replace(needle,needle+'\n    <link rel="stylesheet" th:href="@{/css/internships.css}">',1)
if 'th:src="@{/js/internships.js}"' not in s:
    needle='<script th:src="@{/js/bootcamp.js}"></script>'
    if needle not in s: sys.exit('Could not locate bootcamp JS inclusion')
    s=s.replace(needle,needle+'\n<script th:src="@{/js/internships.js}"></script>',1)
base.write_text(s)
print('Updated base.html. Backup:',backup)
