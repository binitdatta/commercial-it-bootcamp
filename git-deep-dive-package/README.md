# Git Deep Dive — isolated Spring Boot integration

This package adds exactly three files: a controller with an exact `/internships/git-deep-dive` mapping, a Thymeleaf wrapper and the complete training lesson. It does not overwrite `InternshipController.java`, `topic.html`, `section.html`, `base.html`, CSS, security or any existing lessons.

Install from the project root (containing pom.xml):

    python3 /path/to/git-deep-dive-package/install.py

Then build and restart the app. Visit http://localhost:8081/internships/git-deep-dive .

Existing files with the same destination names are rejected unless byte-for-byte identical. The installer checks the project root, route collision in InternshipController.java, and lesson/fragment consistency before copying.
