PixWars
=======

My pet project, an online multiplayer arcade game written in Java, using a server hosted on my Raspberry Pi, 
that includes a built in JavaScript engine for writing custom AI scripts

The game is played by moving with the wasd keys, and firing with the arrow keys. When a player is hit with a shot, 
that player loses some size, and the player who fired the shot gains a proportional amount. 
A tiny bit of size is lost every time a player shoots. Standing still allows one to build energy, denoted by circles 
forming around them. This energy can be used to teleport by hitting shift, to heal by hitting 'h' or to fire faster
more effective shots by holding space before hitting an arrow key.  The goao of the game is to be the largest player.
