# Jeux Othello Intelligence Artificielle
Auteur : Axel Rémini et Laura Wang

## Installation 
* Télécharger notre fichier 
* Lancer le fichier src/app/Main.java
* N'oublier pas d'installer la librairie JavaFX

## Rapport

### Description du jeu

Othello aussi appelé “Reversi” est un jeu de société combinatoire abstrait opposant
deux joueurs. Il se joue sur un plateau carré de taille de 64 cases nommé othellier. Chaque
colonne et ligne sont identifiées de A à H et de 1 à 8 de haut en bas et de gauche à droite.
Les joueurs sont identifiés par la couleur des pièces, noir ou blanc. Il s'agit d'un jeu à
information complète et à somme nulle.
___

### Règle du jeu

####  A. Etat initial du plateau

En début de partie, quatre pièces sont déjà placées au centre de l'othellier. Les pièces
blanches sont à la position d4 et e5, les pièces noires sont placées en e4 et d5.
#### B. Déroulement d'une partie
- Le joueur avec les pièces noires commence.
- A tour de rôle, chacun des joueurs place une pièce de sa couleur
- Les coups valides sont uniquement ceux qui font gagner une ou des pièces
adverses.
- Si un joueur n'a plus de coup valide, alors il passe son tour tant qu'il n'en a pas
- Si les deux joueurs n'ont plus de coup valide, alors la partie se termine

#### C. Capturer les pièces adverses
Lorsque l'on pose une pièce, l'ensemble des pièces adverses encerclé
horizontalement, verticalement ou diagonalement par nos pièces et le coup joué sont
capturées.

#### D. Comment se termine une partie :
- L'ensemble des 64 cases sont remplies.
- Si un des joueurs n'a plus de pièce.
- Les deux joueurs n'ont plus de coup valide.
#### E. Gagner la partie
Le joueur avec le plus de pièce à la fin de la partie gagne.

### Description  du jeu implémenté 

Le jeu implémenté permet à l'utilisateur d'affronter une personne en local,
d'affronter une IA, ou bien de regarder deux IA s'affronter.
Dans les modes de jeu contenant un joueur humain, les coups valide seront marqués
en rouge afin de permettre au joueur d'établir les coups potentiel d'un simple coup d'œil.
#### A. Mode Joueur vs Joueur
Dans ce mode de jeu, deux joueurs s'affrontent.
Les règles du jeu d'Othello sont alors implémentées, les joueurs choisissent où jouer
en cliquant sur la case correspondante.
#### B. Mode Joueur vs IA
Dans ce mode de jeu, un joueur humain affronte une IA.
Le joueur doit alors décider avec quelle pièce il souhaite jouer (Blanc ou Noir) ainsi que
choisir le niveau de difficulté de l'IA qu'il souhaite affronter. Lorsque le joueur effectue un
coup, l'IA répond immédiatement.
#### C. Mode IA vs IA
Dans ce mode de jeu, deux IA s'affrontent. Le spectateur doit alors choisir le niveau de la
première IA qui jouera avec les pièces noires puis le niveau de la seconde IA. Il suffit alors de
cliquer sur une case de l'othellier afin de faire défiler les coups des IA.

### Stratégie de jeu
A. Evaluation d'une position
Plusieurs critères peuvent entrer en jeu dans l'évaluation d'une position. Ceux pris en
compte dans notre projet sont le matériel, la mobilité et la position des pièces.

1. Matériel
Ce critère prend en compte la différence du nombre de pièces entre les deux joueurs.
Il est le plus important en fin de partie, le but du jeu étant de terminer la partie avec plus de
pièces que l'adversaire. Avec une profondeur très importante, il s'agit du critère le plus
efficace. En effet une profondeur de 60 coups permettrait de résoudre le jeu.

2. Mobilité
Ce critère permet d'obtenir une meilleure mobilité que l'adversaire, c’est-à-dire
obtenir plus de coup légal. Le fait d'avoir moins de coup légal peut pousser l'adversaire à
jouer des coups qui lui porte préjudice sur le long terme.

3. Position
Ce critère permet d'obtenir des pièces stables sur le plateau. Une pièce stable est une
pièce difficilement re capturable. C'est par exemple le cas des pièces sur les coins de
l'othellier qui, une fois capturé, ne sont plus capturable.
Les cases ayant le plus de valeur sont donc les coins car elles ne sont pas re
capturable par l'adversaire.
Les cases situées sur les bords permettent de capturer des lignes et des colonnes
entières. Les autres cases sont des cases qui changent souvent de propriétaire tout au long
de la partie.
Comme aux échecs, une partie d'Othello peut se découper en trois grandes phases :
L'ouverture, le milieu de jeu et la fin de partie (Finale). Durant ces différentes phases, les
stratégies évoluent afin de remporter la partie.

4. Ouverture
Cette phase désigne le début de partie, environ les 15 premiers coups. Durant cette
phase, il est trop tôt d'essayer de maximiser ses nombres de pièces. On cherche plutôt à
maximiser sa mobilité c’est-à-dire à augmenter son nombre de coup légal. En effet si on maximise la mobilité en minimisant celle de l'adversaire, on le pousse à jouer des coups qui
lui seront défavorable sur le long terme.
5. Milieu de jeu
Cette phase marque la fin de l'ouverture. Durant cette phase on met l'accent sur la
position des pièces et non pas sur leurs nombres. On cherche alors à obtenir les meilleures
cases de l'othellier.
6. Finale
Cette phase marque les derniers coups de la partie. C'est durant celle-ci que l'on
cherche à remporter la partie. On cherche alors à maximiser son nombre de pièce tout en
minimisant le nombre de pièce de l'adversaire.

### IA implémentés

L'algorithme utilisé par les intelligences artificielles est Minimax avec élagage alpha-
bêta. L'algorithme Minimax s'applique en effet sur des jeux à deux joueurs et à somme nulle,

c’est-à-dire un jeu où la somme des gains et des pertes des joueurs est égale à 0.
Cet algorithme consiste à minimer la perte maximale. Il permet de choisir le coup qui
mène vers la position la moins défavorable (qui peut être pour autant favorable). Le score de
chaque position est attribué par une fonction d'évaluations.
La technique utilisé afin de réduire le nombre de nœuds évaluée par l'algorithme
Minimax est l'élagage alpha-bêta.
La réduction du nombre de nœuds permet d'augmenter la profondeur de calcul ou
encore d'alléger la puissance de calcul à profondeur égale.
Les IA de niveau impaires ont une profondeur de calcul de 3 et les IA de niveau paire
une profondeur de 6.

1. Niveau 1 & 2
Les IA de niveau 1 & 2 ont pour critère d'évaluation d'une position, le critère
matériel. Il est alors facile de les vaincre car elles ne prennent pas en compte la position de
leur pièce. Elles donnent donc autant d'importance à une pièce centrale qu'a une pièce
positionnée dans un coin de l'othellier.
2. Niveau 3 & 4
Les IA de niveau 3 & 4 ont pour critère d'évaluation d'une position, les critères
matériels et le critère de mobilité.
3. Niveau 5 & 6
Les IA de niveau 5 & 6 prennent en compte le matériel, la mobilité et la position. De
plus la stratégie évolue selon les différentes phases de jeu. Durant l'ouverture l'accent sera
porté sur la mobilité et la position. En milieu de jeu, la position est le critère prépondérant.
Enfin en phase finale de la partie, le matériel devient le critère important sans pour autant
délaisser la position des pièces.

### Conclusion
Ce projet nous a permis une meilleure compréhension des algorithmes impliqués dans la théorie
des jeux. Il nous a également permis de mieux comprendre comment les IA effectuent des choix
(fonction d'évaluation).
D’autre part ce projet s’est révélé très enrichissant dans la mesure où il nous a permis de
développé un meilleur esprit d'équipe pour le travail de groupe et un respect des délais pour le
rendu du projet.