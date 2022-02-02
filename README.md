# Kalah Game
Kalah game with Artificial Intelligence that uses the Minimax Algorithm to make decisions. 
AI makes decisions based on the simulation of the game several rounds ahead, depending on the selected difficulty level. 
If we choose the easy level, the most favorable move will be based on a simulation of 3 rounds, in the case of medium level it will be 6 rounds, and the hard level will simulate 9 rounds ahead.
The server waits for moves from players for a selected amount of time and then inserts the bot in the player's place, if he has not made a decision before the time expires.
The bot is based on a simple algorithm that performs random move from among the available ones


## Game modes
1. Player vs Player
2. Player vs Computer
3. Computer vs Computer

## Rules
1. At the beginning of the game, six stones are placed in each house.
2. Each player controls the six houses and their stones on the player's side of the board. The player's score is the number of stones in the store to their right.
3. Players take turns sowing their stones. On a turn, the player removes all stones from one of the houses under their control. Moving counter-clockwise, the player drops one stone in each house in turn, including the player's own store but not their opponent's.
4. If the last sown stone lands in an empty house owned by the player, and the opposite house contains stones, both the last stone and the opposite stones are captured and placed into the player's store.
5. If the last sown stone lands in the player's store, the player gets an additional move. There is no limit on the number of moves a player can make in their turn.
6. When one player no longer has any stones in any of their houses, the game ends. The other player moves all remaining stones to their store, and the player with the most stones in their store wins.
