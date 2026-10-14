#!/bin/bash
set -euo pipefail
cd /Users/binitdatta/Development/Commercial/commercial-it-bootcamp
SRC="aws-iam-policies-roles-package"
T="src/main/resources/templates/internships"
C="src/main/java/com/commercialit/bootcamp/controller"
I="src/main/resources/static/images"
mkdir -p "$T" "$C" "$I"
for f in "$T/aws-iam-policies-roles.html" "$C/AwsIamPoliciesRolesLessonController.java" "$I/aws-iam-policies-roles-sts.png"; do
  if [ -e "$f" ]; then cp "$f" "$f.bak"; fi
done
cp "$SRC/aws-iam-policies-roles.html" "$T/"
cp "$SRC/AwsIamPoliciesRolesLessonController.java" "$C/"
cp "$SRC/aws-iam-policies-roles-sts.png" "$I/"
echo 'Installed. Run mvn clean install, restart Spring Boot.'
