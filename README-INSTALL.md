# Internships module — Spring Boot integration

Designed for your `com.commercialit.bootcamp` Spring Boot application with Thymeleaf `base.html`, Bootstrap 5.3.8, and `bootcamp.css`.

1. Extract this archive **into** `/Users/binitdatta/Development/Commercial/commercial-it-bootcamp` (the project root, containing `pom.xml`).
2. From that project root, run `python3 install-internships.py` once to insert Internships into your existing navbar and load internship assets.
3. Start the application as usual, then browse `/internships`.

The installer preserves the existing Training, E-Commerce Reference, sign-in/sign-out, and user roles sections. Before updating `base.html`, it writes `base.html.before-internships.bak`.

The archive contains the controller, three Thymeleaf pages, scoped CSS, JS, an HTML navigation snippet, and an integration script. It does not overwrite your existing `base.html` during extraction.

These are navigation and curriculum scaffolds, not fully written lessons. The controller defines 8 modules and 17 topic pages. Your `SecurityConfig` may protect `/internships`; access depends on its existing authorization rules.
