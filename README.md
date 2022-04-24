# Boissibook

- [Concept](#concept)
  * [Idées de nom d’app](#idées-de-nom)
  * [Repo Github](#repo-github)
  * [Plateforme](#plateforme)
  * [BDD](#bdd)
  * [Api de recherche de livres](#api-de-recherche-de-livres)
- [Features](#features)
  * [Gestion des utilisateurs](#gestion-des-utilisateurs)
  * [Gestion des livres](#gestion-des-livres)
  * [Readlist](#readlist)
  * [Téléchargement et envoie du livre](#téléchargement-et-envoie-du-livre)
  * [Scrapper Zlib](#scrapper-zlib)

<!-- tocstop -->

## Concept

C'est un utilitaire pour gérer sa collection de livres, à la manière d’un myanimelist, book collector.

On peut gérer sa liste de livre, ses statuts de lecture, son avancement…

Petit aspect social où l’on peut noter un livre et voir la moyenne de ce dernier donné par les différents utilisateurs.
Il sera aussi possible de laisser un commentaire (publique ou pas).

Petite fonctionnalité pour pouvoir télécharger l’ebook, et l'ajouter, si on le possède, pour le partager aux autres
utilisateurs (tout à fait légal, oui oui.). On pourrait également scraper quelques sites pour essayer de le trouver si
on ne le possède pas grâce à un utilitaire intégré (de mieux en mieux !).

### Idées de nom

- Boissibook
- Kindle surprise, Kindle bueno, maxi ... 😏
- ...

### Repo Github

### Plateforme

Google Cloud Platform ou AWS (Si GCP trop dur à utiliser)

### BDD

PostgreSQL (ou MongoDB ?)

### Api de recherche de livres

[Google Books APIs](https://developers.google.com/books/docs/v1/using)

[Open Library](https://openlibrary.org/)

## Features

### Gestion des utilisateurs

- Les use-cases
    - Inscription
    - Connexion
    - Récupération de ses informations
    - Déconnexion
    - Modification

### Gestion des livres

Feature permettant de chercher un livre, l’ajouter à la base s’il n’existe pas encore et récupérer les informations de
ce dernier (y compris sa note et les commentaires publiques laissés par les utilisateurs).

- Les use-cases
    - Chercher un livre en une ligne (qui pourra prendre aussi bien le nom d’un livre que celui d’un auteur, d’un genre).
    - Chercher en ligne par ISBN
    - Enregistrer un livre en base (par ISBN)
    - Chercher un livre en base en une ligne (qui pourra prendre aussi bien le nom d’un livre que celui d’un auteur,
      d’un genre).
    - Récupérer les informations d’un livre en base (par ISBN)
    - Récupérer les commentaires (public) d’un livre en base (par ISBN)

### Readlist

Feature permettant à un utilisateur de gérer sa bibliothèque et ses livres en cours de lecture.

- Les propriétés
    - Visibilité
    - Status de lecture (plan to read, reading, completed, on hold, dropped)
    - Chapitre en cours
    - Note donnée (sur 10)
    - Commentaire
- Les use-cases
    - Récupérer ses livres enregistrés
    - Récupérer un livre enregistré
    - Ajouter un livre dans sa liste
    - Modifier un livre dans sa liste
    - Incrémenter le nombre de chapitres lus (idée de cocher séparément les chapitre lus ?)
    - Noter le livre
    - Changer le commentaire
    - Changer le statut
    - Retirer le livre de sa liste

### Téléchargement et envoie du livre

- Stockage du livre dans un S3 et lien en base
- L’utilisateur peut également ajouter lui-même le livre s’il le possède
- Les uses cases
    - Trouver un fichier via Zlib
      → [Scrapper Zlib](#scrapper-zlib)
    - Récupérer la liste des liens de téléchargement (ordonnée par nombre de téléchargements)
    - Ajouter un fichier livre
    - Supprimer un fichier livre
    - Télécharger le livre

### Scrapper Zlib

Scrapper python, utilisé par l’application Spring pour parcourir Zlib et télécharger le bouquin grâce à son nom, ISBN.

- [FastAPI](https://fastapi.tiangolo.com/)
- [Selenium](https://fr.acervolima.com/principes-de-base-de-selenium-python/)
- Ou [beautifulsoup](https://www.crummy.com/software/BeautifulSoup/bs4/doc/)

Une fois le fichier du livre récupéré
→ [Téléchargement / Envoie du livre ](#téléchargement-et-envoie-du-livre)
