SafetyNet Alerts

Le but de cette application est d'envoyer des informations aux systèmes de services d'urgence.

C’est une API Rest construite avec Spring Boot. Elle utilise un fichier texte jso.data qui contient toutes les données relatives aux personnes, casernes et dossiers médicaux (Person, FireStation, MedicalRecord).

Principales fonctionnalités

- Firestation : Affiche le nombre d’enfants, d’adultes et les coordonnées des gens dépendant d’une Station
- ChildAlert : Affiche la liste des enfants et la liste des adultes habitant à une adresse ou rien s’il n’y a pas d’enfant.
- PhoneAlert : Affiche la liste des numéros de téléphone des gens dépendant d’une Station
- Fire : Affiche les coordonnées civiles et médicales des gens habitant à une adresse
- Flood/stations : Affiche par Station les coordonnées civiles et médicales des gens dépendant d’une liste de Stations.
- PersonInfo : Affiche les coordonnées civiles et médicales d’une personne.
- CommunityEmail : Affiche les adresses mail de tous les habitants d’une ville

Caractéristiques

Prerequisites
• Java 1.8
• Spring Boot 2.7.2


