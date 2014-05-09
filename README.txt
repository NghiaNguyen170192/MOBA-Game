/***************************************************************************
* COSC2440: Software Architecture: Design & Implementation 
* Semester 2013A Assignment #1
* Team: Infinity Defense
* Full Name        : Nguyen Quoc Trong Nghia - s3343711
				     Vuong Do Thanh Huy - s3342135
					 Kieu Hoang Anh - s3275058
****************************************************************************/

-----------------------------------------------------------------------------
If selected, do you grant permission for your assignment to be released as an
anonymous student sample solution?
-----------------------------------------------------------------------------
Yes


Known bugs:
-----------
hero can attack enemy hero although he is out of range to reach
when choose hero, user must click on the radio button to show up the hero list.
(when the form load first time, hero list does not repaint)


Complete functionalities:
-------------------------
usable UI with custom components
team play (2 team, 2 members for each)
multithread chat
broadcast chat
client - Server architecture
basic design document
5 heros available
unit tests cover all core classes
can run on different computer (firewall, proxy off)


Incomplete functionalities:
-------------------------
no animation
the game does not end: no end point in score board
no ranking system
no custom map
no validation for input, choose, click on buttons
no direct 1-1 chat
no implementation of defence towers
no match-marking system
no neutral enemies
no editable terrain/arenas
no AI/bot play


Assumptions:
------------
two team
each team has 2 members
each member should choose different hero


Any other notes for the marker:
-------------------------------
(Turn off firewall and Proxifier if any, on Windows environment)

To start the system:
start the Server > ChatServer > StartChatServer
start the Server > GameServer > StartGameServer
and then run the Client > Main

After that, enter the ip address of the server (127.0.0.1) in the console, press Enter.
The GUI will then be display, login with ID: s3342135, pass: 1234 to play the game.

The GameServer will accept only 2 connections per team.
The third instance will output error in the console and terminate.
The same error will also be thrown when unexpected exit of client occur.
(If anything goes wrong, 2 servers should be fully restarted and restart the client)