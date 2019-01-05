# EasyMow

La tondeuse peut être programmée pour parcourir une surface.
La position d'une tondeuse est représentée par une coordonnée (x,y) et d'une orientation (N,E,W,S).
La pelouse est divisée en grille pour simplifier la navigation.
Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres (D,A,G).

D = Rotaton de 90° à droite.

G = Rotation de 90° à gauche.

A = Avancer.

Ces commandes sont delivrées à partir d'un fichier dont la syntaxe est la suivante :

    5 5
    1 2 N
    GAGAGAGAA
    3 3 E
    AADAADADDA
    0 3 E
    AADAADADDA


La première ligne est pour la surface de la pelouse.
Les deux suivantes représantes respectivement la position de départ et les commandes associées à une tondeuse et ainsi de suite.

Il y a un nombre max de tondeuse pour éviter les collisions.
Pour éviter les collisions on stocke la dernière position des tondeuses.

Le fichier ainsi fait retournera :

    X :1 Y :3 N
    X :5 Y :1 E
    X :0 Y :3 E

Les coordonnées finales des 3 tondeuses disposées sur la pelouse.
Le parser lit le fichier et en même temps run les tondeuses les unes après les autres et les affiches.

Ce projet utilise SBT.

    Pour compiler :
    > sbt compile

    Pour exécuter :
    > sbt run

    Pour tester :
    > sbt test

