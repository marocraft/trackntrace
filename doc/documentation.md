# Documentation TnT

## Introduction

### Pain points

On a tous eu l'occasion de consulter des logs soit pour chercher la cause d’une exception ou juste pour s’assurer que le output d’une application est le même avec ce qu’on attendais;
Alors imaginons une situation: on est en plein prod tout va bien on est comptant soudain l’application s’explose, les appelles commencent et on est bloqué, alors si les ops ont une plateform de monitoring ils doivent consulter les logs de chaque développeur parcequ’ils a eu du mal à agréger les logs de l’ensemble des développeurs parce que chaque développeur écrit les logs à ca manière  donc beaucoup plus de temps et de stress pour localiser l’anomalie et  c’est une tache pénalisante.

Les développeurs de leur côté écrivent les logs mannuellement: pour chaque méthode d’une class  on doit écrire du code pour avoir des logs ce qui signifie encore plus de temps de dev.

Pour résumer ces points:

- Écrire les logs techniques manuellement est une tâche répétitive et lourde

- Chacun écrit les logs à sa manière

- Difficulté de centraliser le format des logs

- Complexité d’interfaçer les logs avec une plateforme de monitoring telle que la Stack ELK

### Solution

C’est effectivement pour les Pain points déclarés en decus que nous avons pensé à créer mécanisme simplifié, unifié de logging facilement plugable dans des application java basées sur spring boot qui facilite la tache pour les développeur et les ops.

On peut le considérer comme un contrat entre eux.

## Schéma TnT

Voici un schéma qui explique le fonctionnement du framework TnT

<img src="./images/schema-tnt.jpg"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" >&nbsp;

 Techniquement parlant, ceux sont des annotations ciblant les différentes couches applicatives avec un comportement adapté selon leurs types et manières d’utilisation.
Le Framework ne remplace pas les Framework de logging comme log4j2 ou logback.Par contre il permet de s’interfacer avec eux afin de débarrasser le développeur de développer la partie logging automatique dont l’objectif est de mettre plus de focus sur les logs techniques tout en gardant un niveau de logging acceptable et auditable.

## Promesses

- Débarrasser le développeur des douleurs de logging

- Faire un code plus propre

- Simplifier le suivi de l’exécution d’une application

- Contrat entre DEV et OPS pour uniformiser les logs techniques sur ELK

- Gagner  plus temps dans l’analyse des logs

## Que devons nous Tracer ?

- What

  - Resource in use.
  - Method in use
  - Return code
- Who
  - Current user
  - Application in use
- When
  - Call date and time
  - Response date and time
  - Duration
- where

## Comment intégrer TnT ?

### Step1

Si vous êtes sur un projet Maven ajoutez la dépendance suivante dans votre fichier pom.xml

<img src="./images/tnt-dep.jpg"
     alt="Markdown Monster icon"
     style="float: left; margin-right: 10px;" >&nbsp;

### Step2

Configurer le fichier application.properties  ou le fichier application.yml pour personnaliser le format de la Template

```json
tnt:
 logging:
  format:
   default: >
    {"methodName": "{{methodName}}",
    "className": "{{className}}",
    "logLevel": "{{logLevel}}",
    "executionTime": "{{executionTime}}",
    "logMessage": "{{logMessage}}",
    "timeStamps": "{{timeStamps}}",
    "traceId": "{{traceId}}",
    "spanId": "{{spanId}}",
    "parentId": "{{parentId}}",
    "ip": "{{ip}}"}
   rest: >
    {"methodName": "{{methodName}}",
    "className": "{{className}}",
    "logLevel": "{{logLevel}}",
    "executionTime": "{{executionTime}}",
    "logMessage": "{{logMessage}}",
    "timeStamps": "{{timeStamps}}",
    "httpVerb": "{{httpVerb}}",
    "httpStatus": "{{httpStatus}}",
    "httpURI": "{{httpURI}}",
    "traceId": "{{traceId}}",
    "spanId": "{{spanId}}",
    "parentId": "{{parentId}}",
    "ip": "{{ip}}"}
  output: json
 multithread:
  poolsize: 1
 correlation:
  traceid: x-b3-traceid
  spanid: x-b3-spanid
  parentspanid: x-b3-parentspanid
```

- la partie format contient deux types:
  - default: c'est le format qui sera logger par defaut.
  - rest: c'est le format qui sera logger uniquemnt dans les class rest
- methodName: c'est le nom de la méthode annotée par @Trace.
- className: le nom de la class qui contient la méthode annotée.
- logLevel: représente le niveau de log: TRACE, DEBUG, INFO, WARN, ERROR.
- executionTime: c'est la durée d'exécution de la méthode.
- logMessage: le message transferé dans les paramètre de l'annotation trace.
- timeStamps: cest la date et le temps exprimées avec le format. suivant : yyyy-MM-dd'T'HH:mm:ss.nnnnnnnnn X.
  - yyyy: l'année.
  - MM: le mois.
  - dd: le jour du mois.
  - HH: l'heure du jour.
  - ss: les secondes.
  - nnnnnnnnn: les nano secondes.
  - X: le "offset" du pays par rapport à l'UTC.
- traceId: c'est un identifiant pour tracer une requette HTTP et c'est identique dans les appels successives des services de la même requette.
- spanid: c'est un identifiant spécifique pour chaque service.
- parentid: (ou parent spann id) il prend la veleur du span précedent afin d'identifier le span parent.
- ip: l'adresse ip public du client
- httpVerb: les méthodes HTTP dees services Restful: POST, GET, PUT, PATCH, DELETE.
- httpStatus: les status de la requet: 200, 400...
- httpURI: c'est l'uri de la requet http.
- output: le format de l'output (actuellemnt supporte que Json).
- poolsize: le nombre de threads sur lesquels TnT sera démaré.
- correlation.traceid: le nom de la varibale traceid.
- correlation.spanid: le nom de la variable span.
- correlation.parentspanid: le nom de la variable parentspanid.

### Remarque

- On peut choisir les champs qui nous intéresses, pour les logger.
- on peut ajouter des champs statiques

### Step3

Ajoutez le fichier logback.xml à votre src/main/resources:

- Spécifiez le pattern comme indiqué dans la config logback.xml
- Spécifiz un nouveau logger qui a comme nom:
  - com.github.marocraft.trackntrace

<img src="./images/config-logger.png"
     alt="Markdown Monster icon"
    >&nbsp;

### Step4

Ajoutez l’annotation @EnableTracknTrace à la class main de votre projet pour activer ou désactiver TnT

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.github.marocraft.trackntrace.annotation.EnableTracknTrace;

@SpringBootApplication
@EnableTracknTrace
@ComponentScan(basePackages = { "com.organisation" })
public class DemoTnt {
    public static void main(String[] args) {
        SpringApplication.run(DemoTnt.class, args);
    }
}
```

### Step5

Ajoutez l’annotation @Trace  à la méthode que vous voulez tracer dans vos logs

 ``` java

    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RestController;

    import com.github.marocraft.trackntrace.annotation.Trace;
    import com.github.marocraft.trackntrace.domain.LogLevel;


    @RestController
    public class RestControllerTnT {
        @Trace(message="my message",level=LogLevel.DEBUG)
        @GetMapping(value = "/hello")
        public String getTntLogs() {
            return "works";
        }
  }
```

Le résultat est présenté actuellement sous forme de Json et contient les champs qui ont été déclarés dans la config

  ```json
  {
   "methodName":"getTntLogs",
   "className":"com.marocraft.contact.infrastructure.http.ressources.RestControllerTnT",
   "logLevel":"DEBUG",
   "executionTime":"0",
   "logMessage":"my message",
   "timeStamps":"2019-07-12T14:35:17.469000000",
   "serviceRequest":"GET",
   "serviceResponse":"200",
   "serviceUri":"/hello",
   "x-b3-traceid":"88380b63fadeb07c",
   "x-b3-spanid":"38612b63fadeb06e",
   "x-b3-parentid":"52369b63fadec35b",
   "project":"my project",
   "static_field1":"ap23883",
   "static_field2":"abc123",
   "ip":"0:0:0:0:0:0:0:1"
}
  ```
