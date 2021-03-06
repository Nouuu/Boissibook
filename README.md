# Boissibook

| [![Quality Gate Status](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=alert_status&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Reliability Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=reliability_rating&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Security Rating](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=security_rating&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Technical Debt](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=sqale_index&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Bugs](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=bugs&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Code Smells](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=code_smells&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) | [![Coverage](https://sonar.nospy.fr/api/project_badges/measure?branch=dev&project=Nouuu_Boissibook&metric=coverage&token=679473caa97f7d7d9df109022b2db2d90c5fea1f)](https://sonar.nospy.fr/dashboard?id=Nouuu_Boissibook&branch=dev) |
|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|

<!-- toc -->

- [Concept](#concept)
  * [Id??es de nom](#id??es-de-nom)
  * [Api de recherche de livres](#api-de-recherche-de-livres)
- [D??p??ts Github](#d??p??ts-github)
- [Architecture Google Cloud Platform & CI/CD](#architecture-google-cloud-platform--cicd)
- [Features](#features)
  * [Gestion des utilisateurs](#gestion-des-utilisateurs)
    + [Fonctionnalit??s](#fonctionnalit??s)
  * [Gestion des livres](#gestion-des-livres)
    + [Fonctionnalit??s](#fonctionnalit??s-1)
  * [Readlist](#readlist)
    + [Fonctionnalit??s](#fonctionnalit??s-2)
  * [T??l??chargement et envoie du livre](#t??l??chargement-et-envoie-du-livre)
    + [Fonctionnalit??s](#fonctionnalit??s-3)
  * [Achievements](#achievements)
  * [Scrapper Zlib](#scrapper-zlib)
- [Application frontend](#application-frontend)
- [Choix d'impl??mentations](#choix-d'impl??mentations)
  * [Hexagonal architecture](#hexagonal-architecture)
    + [Architecture en couche](#architecture-en-couche)
  * [Diagrammes de s??quence](#diagrammes-de-s??quence)
    + [Ajout d'un utilisateur](#ajout-dun-utilisateur)
    + [Recherche d'un livre](#recherche-dun-livre)
    + [Ajout d'une review sur un livre](#ajout-dune-review-sur-un-livre)
  * [Tests](#tests)
    + [Tests d'architecture](#tests-d'architecture)
    + [Tests unitaires](#tests-unitaires)
    + [Tests de contrat avec test container](#tests-de-contrat-avec-test-container)
    + [Tests E2E](#tests-e2e)

<!-- tocstop -->

## Concept

C'est un utilitaire pour g??rer sa collection de livres, ?? la mani??re d???un myanimelist, book collector.

On peut g??rer sa liste de livre, ses statuts de lecture, son avancement???

Petit aspect social o?? l???on peut noter un livre et voir la moyenne de ce dernier donn?? par les diff??rents utilisateurs.
Il sera aussi possible de laisser un commentaire (publique ou pas).

Petite fonctionnalit?? pour pouvoir t??l??charger l???ebook, et l'ajouter, si on le poss??de, pour le partager aux autres
utilisateurs (tout ?? fait l??gal, oui oui.). On pourrait ??galement scraper quelques sites pour essayer de le trouver si
on ne le poss??de pas gr??ce ?? un utilitaire int??gr?? (de mieux en mieux !).

### Id??es de nom

- Boissibook
- Kindle surprise, Kindle bueno, maxi ... ????
- ...

### Api de recherche de livres

[Google Books APIs](https://developers.google.com/books/docs/v1/using)

[Open Library](https://openlibrary.org/)

## D??p??ts Github

- [Boissibook](https://github.com/Nouuu/Boissibook)
- [Application Swift](https://github.com/RemyMach/boissibook-swift)
- [Scrapper Zlib](https://github.com/RemyMach/Boissibook-scraper)

## Architecture Google Cloud Platform & CI/CD

L'application est enti??rement d??ploy??e sur Google Cloud Platform, avec une infrastructure de d??ploiement automatique.

![](./doc/README-1658772052065.png)

## Features

### Gestion des utilisateurs

Ce usecase est assez classique, elle permet de g??rer les utilisateurs.

Un utilisateur est d??fini par les propri??t??s suivantes :

```json
{
  "userId": {
    "type": "string",
    "description": "The user's id"
  },
  "email": {
    "type": "string",
    "description": "The user's email",
    "example": "gregory@mail.com"
  },
  "name": {
    "type": "string",
    "description": "The user's name",
    "example": "Gregory"
  }
}
```

#### Fonctionnalit??s

Les diff??rentes fonctions sont les suivantes :

- Cr??er un utilisateur
- Modifier un utilisateur
- Supprimer un utilisateur
- Supprimer tous les utilisateurs
- R??cup??rer un utilisateur
- R??cup??rer un utilisateur par son email
- R??cup??rer la liste des utilisateurs
- Compter le nombre d???utilisateurs

### Gestion des livres

Feature permettant de chercher un livre, l???ajouter ?? la base s???il n???existe pas encore et r??cup??rer les informations de
ce dernier (y compris sa note et les commentaires publics laiss??s par les utilisateurs).

Un livre est d??fini par les propri??t??s suivantes :

```json
{
  "id": {
    "type": "string"
  },
  "title": {
    "type": "string"
  },
  "authors": {
    "type": "array",
    "items": {
      "type": "string"
    }
  },
  "publisher": {
    "type": "string"
  },
  "publishedDate": {
    "type": "string"
  },
  "description": {
    "type": "string"
  },
  "isbn13": {
    "type": "string"
  },
  "language": {
    "type": "string"
  },
  "imgUrl": {
    "type": "string"
  },
  "pages": {
    "type": "integer",
    "format": "int32"
  }
}
```

#### Fonctionnalit??s

Les diff??rentes fonctions sont les suivantes :

- Chercher un livre en une ligne (qui pourra prendre aussi bien le nom d???un livre que celui d???un auteur, d???un genre)
  .
- Chercher en ligne par ISBN
- Enregistrer un livre en base (par ISBN)
- Chercher un livre en base en une ligne (qui pourra prendre aussi bien le nom d???un livre que celui d???un auteur,
  d???un genre).
- Supprimer un livre en base
- R??cup??rer les informations d???un livre en base (par ISBN)
- R??cup??rer les commentaires (public) d???un livre en base (par ISBN)

### Readlist

Feature permettant ?? un utilisateur de g??rer sa biblioth??que et ses livres en cours de lecture.

Une review est d??finie par les propri??t??s suivantes :

```json
{
  "bookProgressionId": {
    "type": "string",
    "description": "The id of the readlist item",
    "example": "7bd1b206-833d-4378-8064-05b162d80764"
  },
  "bookId": {
    "type": "string",
    "description": "The id of the book",
    "example": "7bd1b206-833d-4378-8064-05b162d80764"
  },
  "userId": {
    "type": "string",
    "description": "The id of the user",
    "example": "7bd1b206-833d-4378-8064-05b162d80764"
  },
  "readingStatus": {
    "type": "string",
    "description": "The reading status of the book",
    "example": "READING"
  },
  "visibility": {
    "type": "string",
    "description": "The visibility of the review",
    "example": "PUBLIC"
  },
  "currentPage": {
    "type": "integer",
    "description": "The number of the current page",
    "format": "int32",
    "example": 12
  },
  "note": {
    "type": "integer",
    "description": "The note given to the book",
    "format": "int32",
    "example": 5
  },
  "comment": {
    "type": "string",
    "description": "The comment of the review",
    "example": "This book is awesome"
  }
}
```

#### Fonctionnalit??s

Les diff??rentes fonctions sont les suivantes :

- R??cup??rer une review par son id
- Mettre ?? jour sa review sur un livre
- Supprimer sa review sur un livre
- Ajouter une review sur un livre
- Mettre ?? jour son statut de lecture sur un livre
- Mettre ?? jour sa note sur un livre
- Mettre ?? jour son commentaire sur un livre
- Mettre ?? jour sa progression sur un livre
- R??cup??rer toutes les reviews d???un utilisateur
- R??cup??rer toutes les reviews d???un livre

### T??l??chargement et envoie du livre

La fonctionnalit?? phare et tout ?? fait l??gale (????) de Boissibook. Il est possible d'ajouter sa propre version num??rique
d'un livre.<br/>
Si vous ne poss??dez pas le livre, pas de probl??me ! Un autre utilisateur l'a peut ??tre d??j?? ajout?? ?? votre place. Sinon,
vous pouvez demander ?? Boissibook de tenter de le t??l??charger pour vous (dans la limite du quota de 5 par jours).

Un fichier de livre est d??fini par les propri??t??s suivantes :

```json
 {
  "id": {
    "type": "string",
    "description": "Book file id"
  },
  "name": {
    "type": "string",
    "description": "Book file name"
  },
  "type": {
    "type": "string",
    "description": "Book file type"
  },
  "bookId": {
    "type": "string",
    "description": "Book id"
  },
  "userId": {
    "type": "string",
    "description": "User who uploaded id"
  },
  "downloadCount": {
    "type": "integer",
    "description": "File download count",
    "format": "int32"
  }
}
```

#### Fonctionnalit??s

- Ajouter sa version num??rique d'un livre
- Trouver un fichier via Zlib
  ??? [Scrapper Zlib](#scrapper-zlib)
- R??cup??rer la liste des liens de t??l??chargement (ordonn??e par nombre de t??l??chargements) d'un livre
- R??cup??rer le nombre de fichiers de livres disponibles pour un livre
- Supprimer un fichier livre
- T??l??charger un livre

### Achievements

Pour un peu plus de FUN, Boissibook propose des achievements. Ces derniers s'obtiennent lorsque vous avez termin?? un
certain nombre de livres ou qu'un de vos livres ajout?? ?? la biblioth??que a ??t?? t??l??charg?? plusieurs fois.

### Scrapper Zlib

Scrapper python, utilis?? par l???application Spring pour parcourir Zlib et t??l??charger le bouquin gr??ce ?? son nom, ISBN.

- [FastAPI](https://fastapi.tiangolo.com/)
- [Selenium](https://fr.acervolima.com/principes-de-base-de-selenium-python/)
- Ou [beautifulsoup](https://www.crummy.com/software/BeautifulSoup/bs4/doc/)

Une fois le fichier du livre r??cup??r??
??? [T??l??chargement / Envoie du livre ](#t??l??chargement-et-envoie-du-livre)

## Application frontend

Afin de pouvoir exploiter la puissance de Boissibook, nous avons d??velopp?? une application IOS en Swift.

| ![](./doc/README-1658842903759.png) | ![](./doc/README-1658843850902.png) |
|-------------------------------------|-------------------------------------|
| ![](./doc/README-1658843859147.png) | ![](./doc/README-1658843868080.png) |

## Choix d'impl??mentations

### Hexagonal architecture

L???objectif principal de l???architecture hexagonale est de d??coupler la partie m??tier d???une application de ses services
techniques. Ceci dans le but de pr??server la partie m??tier pour qu???elle ne contienne que des ??l??ments li??s aux
traitements fonctionnels. Cette architecture est aussi appel??e ???Ports et Adaptateurs??? car l???interface entre la partie
m??tier et l???ext??rieur se fait, d???une part, en utilisant les ports qui sont des interfaces d??finissant les entr??es ou
sorties et d???autre part, les adaptateurs qui sont des objets adaptant le monde ext??rieur ?? la partie m??tier.

#### Architecture en couche

L???architecture hexagonale pr??conise une version simplifi??e de l???architecture en couches pour s??parer la logique m??tier
des processus techniques.

La logique m??tier doit se trouver ?? l???int??rieur de l???hexagone. Nous prenons plusieurs concepts en compte pour affiner
cette architecture tel que :

- Inversion de d??pendances
- Couche applicative
- Couche infrastructure
- ...

La couche applicative ne doit contenir que le m??tier de notre application, toutes ses d??pendances doivent ainsi ??tre des
interfaces m??tiers, qui seront ensuite inject??es et impl??ment??es par la couche infrastructure.

| Couches                                                                            | Couche Applicative                                              |
|------------------------------------------------------------------------------------|-----------------------------------------------------------------|
| ![Layer_Architecture_-_Hexagonal_Architecture.png](./doc/README-1658825410490.png) | ![Hexagonal_Architecture-1.png](./doc/README-1658825959474.png) |

### Diagrammes de s??quence

Voici quelques diagrammes de s??quence montrant un workflow "Classique" de nos cas d'utilisations.

#### Ajout d'un utilisateur

Lors de l'ajout d'un utilisateur, plusieurs choses se d??roulent :

1. Transformation de l'objet utilisateur provenant de la requ??te en un objet utilisateur m??tier.
2. Appel du service m??tier d'enregistrement de l'utilisateur.
3. R??cup??ration d'un nouvel ID pour l'enregistrement de l'utilisateur.
4. Enregistrement de l'utilisateur dans la base de donn??es.
5. Envoie d'un ??v??nement de cr??ation d'utilisateur.
6. Retour au client de confirmation de l'enregistrement de l'utilisateur, avec en en-t??te le lien pour consulter
   l'utilisateur cr????.

![](doc/UserCommandController_createUser.svg)

#### Recherche d'un livre

Lorsque l'on cherche un livre ?? travers l'API (Recherche google), plusieurs choses se d??roulent :

1. R??cup??ration du terme de la recherche
2. Appel du service m??tier de recherche de livre.
3. Appel du moteur de recherche (en l'occurrence, celui de Google)
4. R??cup??ration des r??sultats de la recherche.
5. Transformation de l'objet de r??sultat de la recherche en un objet de r??sultat de recherche m??tier.
6. Renvoie des r??sultats de la recherche au client.

![](doc/BookSearchRequestController_search.svg)

#### Ajout d'une review sur un livre

L'ajout d'une review sur un livre se passe comme suit :

1. Transformation de l'objet review provenant de la requ??te en un objet review m??tier.
2. V??rification de l'existence du livre
3. V??rification de l'existence de l'utilisateur
4. V??rification de l'existence d'une pr??c??dente review pour cet utilisateur sur ce livre (Si c'est le cas on d??clenche
   une erreur).
5. Appel du service m??tier d'enregistrement de la review.
6. R??cup??ration d'un nouvel ID pour l'enregistrement de la review.
7. Enregistrement de la review dans la base de donn??es.
8. Envoie d'un ??v??nement de cr??ation de review.
9. Retour au client de confirmation de l'enregistrement de la review, avec en en-t??te le lien pour consulter la review
   cr????e.

![](doc/ReadlistCommandController_createBookReview.svg)

### Tests

Afin de garantir que notre application fonctionne correctement, nous avons mis en place plusieurs types de tests.
Ces derniers sont automatiquement ex??cut??s lorsque nous faisons un nouveau d??ploiement et peut interrompre ce dernier
s'ils ne se valident pas tous.

#### Tests d'architecture

Gr??ce ?? la librairie **Arch Unit**, nous v??rifions que notre application respecte les sp??cifications de l'architecture
hexagonale.

Pour ce faire, nous allons valider trois choses :

- Les ??l??ments du domaine ne doivent jamais importer d'??l??ments du framework **Spring** ou **Javax**
- Les ??l??ments du kernel ne doivent jamais importer d'??l??ments du framework **Spring** ou **Javax**
- L'infra peut importer des ??l??ments du framework **Spring** ou **Javax** ou du domaine, mais pas l'inverse.

> Exemple d'un test avec **Arch Unit**

```java
class ArchitectureTest {
    @Test
    void should_domain_never_be_linked_with_frameworks() {
        var ruleNoFramework = noClasses().that().resideInAPackage("..domain..")
                .should().dependOnClassesThat().resideInAPackage("..springframework..")
                .orShould().dependOnClassesThat().resideInAPackage("javax..");

        ruleNoFramework.check(projectClasses);
    }
}
```

#### Tests unitaires

Nous avons d??cid?? de mettre en place des tests unitaires pour nos classes de domaine.
Nos tests unitaires sont compl??tement s??par??s du framework **Spring** et **Javax**, ce dernier n'est absolument pas
pr??sent.

> Exemple de tests unitaires sur la partie utilisateur

```java
class UserCommandHandlerTest {
    // ...
    @BeforeEach
    void setUp() {
        userRepository = new InMemoryUserRepository();
        userCommandHandler = new UserCommandHandler(userRepository, new VoidEventService());
        // ...
    }

    @Test
    void createUser() {
        var userId = userCommandHandler.createUser(user1);

        assertThat(userId).isNotNull();
        assertThat(userRepository.find(userId))
                .isNotNull()
                .isEqualTo(user1.setId(userId));
    }

    @Test
    void updateUser() {
        userRepository.save(user1.setId(userRepository.nextId()));

        user1.setName("newName")
                .setPassword(null);

        userCommandHandler.updateUser(user1);
        assertThat(userRepository.find(user1.id()))
                .isEqualTo(user1.setPassword("password"));
    }
    // ...
}
```

#### Tests de contrat avec test container

Dans le cas de nos impl??mentations de nos interfaces de **Repository**, nous souhaitons tester le bon fonctionnement de
nos m??thodes faisant appel ?? la base de donn??e. Pour se mettre en situation r??elle, il nous faut donc une vraie base de
donn??e pour effectuer nos tests.

De plus, nous avons ??galement une impl??mentation de nos **Repositories** en m??moire et nous devons nous assurer que
cette derni??re a le m??me comportement que la base de donn??e. Ainsi nous nous assurons de ne pas avoir de comportements
inattendus en changeant d'une impl??mentation ?? l'autre.

Cela nous permet ??galement pour les autres tests unitaires de n'utiliser que la base en m??moire pour nous affranchir
totalement de Spring, sans prendre le risque de passer ?? c??t?? de quelque chose.

Pour ce faire, nous allons utiliser la librairie [Testcontainers](https://www.testcontainers.org) pour pouvoir monter ??
la vol??e un conteneur Docker d'une base de donn??e enti??rement d??di??e aux tests.

Lorsqu'une classe de tests n??cessite une base de donn??e, nous allons lui faire impl??menter l'interface suivante afin de
lui faire monter un conteneur docker.

> PostgresIntegrationTest

```java
public abstract class PostgresIntegrationTest {
    private static final PostgreSQLContainer POSTGRES_SQL_CONTAINER;

    static {
        POSTGRES_SQL_CONTAINER = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14-alpine"));
        POSTGRES_SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void overrideTestProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", POSTGRES_SQL_CONTAINER::getPassword);
    }
}
```

Cette derni??re va venir surcharger les param??tres Spring pour lui faire se connecter ?? la base de donn??e
automatiquement.

> UserRepositoryTest

```java

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
class UserRepositoryTest extends PostgresIntegrationTest {

    @Autowired
    JPAUserRepository jpaUserRepository;

    private final static String springDataUserRepositoryKey = "SpringDataUserRepository";

    private final static String inMemoryUserRepositoryKey = "InMemoryUserRepository";

    private HashMap<String, UserRepository> userRepositories;

    // ...

    @BeforeEach
    void setUp() {
        SpringDataUserRepository userRepository = new SpringDataUserRepository(jpaUserRepository);
        InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();

        userRepositories = new HashMap<>();
        userRepositories.put(springDataUserRepositoryKey, userRepository);
        userRepositories.put(inMemoryUserRepositoryKey, inMemoryUserRepository);

        // ...
    }

    private static Stream<String> provideRepositories() {
        return Stream.of(
                springDataUserRepositoryKey,
                inMemoryUserRepositoryKey
        );
    }

    @ParameterizedTest
    @MethodSource("provideRepositories")
    void save(String userRepositoryKey) {
        UserRepository userRepository = userRepositories.get(userRepositoryKey);

        userRepository.save(user1);

        assertThat(userRepository.find(user1.id()))
                .isEqualTo(user1);
    }
}
```

Gr??ce aux `ParameterizedTest`, nous allons jouer les m??mes tests aussi bien sur la base de donn??e r??elle que celle en
m??moire, afin de nous assurer que chacune valide exactement les m??mes tests.

#### Tests E2E

Afin de pouvoir tester les fonctionnalit??s de notre application, nous devons tester que l'application fonctionne de bout
en bout avec un cas d'utilisation r??el. Il faut alors lancer l'application avec tout le context **Spring**, ainsi
qu'avec **Testcontainers** pour avoir un comportement r??el. Nous nous servons ensuite de la
librairie [RestAssured](https://rest-assured.io) pour faire des requ??tes sur l'API afin de s'assurer que le comportement
est bien celui attendu.

> UserCommandsAPITest

```java

@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@Testcontainers
@SpringBootTest(webEnvironment = RANDOM_PORT)
@ActiveProfiles("test")
class UserCommandsAPITest extends PostgresIntegrationTest {
    //...
    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.filters(new RequestLoggingFilter(), new ResponseLoggingFilter());
    }

    @Test
    void createUser() {

        var getUserUri = given()
                .contentType(JSON)
                .body(validUser1)
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .extract()
                .header("location");

        var user = given()
                .baseUri(getUserUri)
                .when()
                .get()
                .then().statusCode(200)
                .extract()
                .body().as(UserResponse.class);

        assertThat(user.userId()).isNotNull();
        assertThat(user.email()).isEqualTo(validUser1.email());
        assertThat(user.name()).isEqualTo(validUser1.name());
    }
    //...
}
```