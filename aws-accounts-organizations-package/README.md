# AWS Accounts & Organizations Lesson Package

This package adds a dedicated lesson page and a PNG architecture diagram for:

`/internships/cloud-computing/aws/accounts-organizations`

## Included files

- `aws-accounts-organizations.html` — Thymeleaf lesson fragment
- `lesson-aws-accounts-organizations.html` — Thymeleaf wrapper using `base.html`
- `AwsAccountsOrganizationsLessonController.java` — dedicated Spring MVC route
- `aws-accounts-organizations.png` — architecture diagram
- `install.sh` — installation script

## Install

From the project root:

```bash
unzip ~/Downloads/aws-accounts-organizations-package.zip
bash aws-accounts-organizations-package/install.sh
mvn clean install
```

Then restart the app and browse:

`http://localhost:8081/internships/cloud-computing/aws/accounts-organizations`
