#!/bin/bash
set -euo pipefail

PROJECT_ROOT="$(pwd)"
PKG_DIR="aws-accounts-organizations-package"
TEMPLATES_DIR="src/main/resources/templates/internships"
CONTROLLERS_DIR="src/main/java/com/commercialit/bootcamp/controller"
IMAGES_DIR="src/main/resources/static/images"

if [ ! -f "$PKG_DIR/aws-accounts-organizations.html" ]; then
  echo "ERROR: Run this script from the project root after unzipping aws-accounts-organizations-package.zip"
  exit 1
fi

mkdir -p "$TEMPLATES_DIR" "$CONTROLLERS_DIR" "$IMAGES_DIR"

for f in \
  "$TEMPLATES_DIR/aws-accounts-organizations.html" \
  "$TEMPLATES_DIR/lesson-aws-accounts-organizations.html" \
  "$CONTROLLERS_DIR/AwsAccountsOrganizationsLessonController.java" \
  "$IMAGES_DIR/aws-accounts-organizations.png"; do
  if [ -f "$f" ]; then
    cp "$f" "$f.bak"
  fi
done

cp "$PKG_DIR/aws-accounts-organizations.html" "$TEMPLATES_DIR/"
cp "$PKG_DIR/lesson-aws-accounts-organizations.html" "$TEMPLATES_DIR/"
cp "$PKG_DIR/AwsAccountsOrganizationsLessonController.java" "$CONTROLLERS_DIR/"
cp "$PKG_DIR/aws-accounts-organizations.png" "$IMAGES_DIR/"

chmod 644 "$TEMPLATES_DIR/aws-accounts-organizations.html" \
          "$TEMPLATES_DIR/lesson-aws-accounts-organizations.html" \
          "$CONTROLLERS_DIR/AwsAccountsOrganizationsLessonController.java" \
          "$IMAGES_DIR/aws-accounts-organizations.png"

cat <<MSG
Installed:
  - $TEMPLATES_DIR/aws-accounts-organizations.html
  - $TEMPLATES_DIR/lesson-aws-accounts-organizations.html
  - $CONTROLLERS_DIR/AwsAccountsOrganizationsLessonController.java
  - $IMAGES_DIR/aws-accounts-organizations.png

Next steps:
  1. mvn clean install
  2. Restart the Spring Boot application
  3. Open http://localhost:8081/internships/cloud-computing/aws/accounts-organizations
MSG
