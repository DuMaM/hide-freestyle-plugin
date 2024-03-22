# lock-project-plugin

This plugin allows you to lock creation of selected jobs types in Jenkins.
It's useful when you want to enforce standards for job types used in your company.

![Plugin options](./src/docs/image.png#center "Plugin configuration options")

## Features

* blocks creation of selected job types from jenkins system config
* allows to add exception for admins so when you want to move/rename old freestyle job it will not lock you
  
### Freestyle

Since freestyle jobs are part of Jenkins core, they are always available.
You cannot block their creation, also many plugins depend on them.
This plugin allow you to hide them and enforce for example pipeline creations.

### Maven

Maven pipeline plugin is a popular choice for Jenkins users.
It's one dependency is `maven-plugin` which provides classic ui configured maven and maven sets jobs.
If you want to store all your jobs in pipeline, with this plugin you can block creation of them.

## Notes

* This project is still in beta, so some settings my change in the future, i will be informing about them in release notes.
* Project is based on:
  * https://github.com/jenkinsci/archetypes config plugin example
  * https://github.com/daniel-beck/hide-maven-plugin/tree/master job descriptor implementation

## Known Issues

* changes require Jenkins restart to take effect

## Contributions

Contributions are welcome, please see [CONTRIBUTING.md](CONTRIBUTING.md) for more information.
You can find there TODO list and some guidelines for development.
