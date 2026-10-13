# AWS & Google Cloud hierarchical menus — Installation

This package extends only the existing shared navbar (`base.html`) and adds
**two dedicated Spring MVC catalog controllers** plus one shared placeholder template.
All existing Azure menu entries, their Javascript and their controllers are retained.
The existing /internships/cloud-computing/aws and /google-cloud overview URLs stay intact.

## Install on your Mac (copy/paste as one block)

```bash
cd /Users/binit.datta/Development/OpenSource/commercial-it-bootcamp
cp src/main/resources/templates/base.html src/main/resources/templates/base.html.before-aws-gcp-menus.bak
unzip -o ~/Downloads/aws-gcp-hierarchical-menus.zip
```

Restart the Spring Boot app in IntelliJ. Click:
Internships > Cloud Computing > Amazon Web Services (AWS) > Category > Lesson
and repeat for Google Cloud (GCP).

## Routing and future lesson development

Every topic is backed by the corresponding dedicated controller:
- `AwsTrainingCatalogController.java`: `/internships/cloud-computing/aws/{lessonSlug}`
- `GoogleCloudTrainingCatalogController.java`: `/internships/cloud-computing/google-cloud/{lessonSlug}`

For now these URLs show the **same generic placeholder template** with provider/category/topic values.
They do NOT pretend that full lessons exist.

When creating a real lesson later:
1. Create a dedicated lesson HTML in `templates/internships/`.
2. Register a dedicated controller route for that *exact* URL and remove that topic from
   the matching catalog, or update that catalog's lesson route to select the new template.
3. Do not create a duplicate ambiguous GET mapping. In particular, static paths can take
   precedence over template paths, but avoid relying on that when organizing your code.
4. Keep the existing navbar link/URL unchanged.

## Notes

- Existing Azure remains an in-place click accordion as before.
- AWS and GCP use in-place provider/category accordions rather than fragile fourth-level flyouts.
- No cloud resources are provisioned and no Azure/AWS/GCP service pages are implemented yet.
- If you have since changed your local `base.html` after the Logic Apps package, compare
  the navbar before overwriting it; this package uses that confirmed known baseline.
