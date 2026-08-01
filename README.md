# Tic Tac Toe

A simple 2-player Tic Tac Toe game with a Tkinter GUI.

## Tech stack

- **Language:** Python 3 (no third-party dependencies)
- **UI:** [Tkinter](https://docs.python.org/3/library/tkinter.html), Python's built-in GUI toolkit
  - `tkinter.Tk` / `tkinter.Frame` / `tkinter.Button` / `tkinter.Label` for the window and board
  - `tkinter.messagebox` for the win/tie dialog

## How to play

```
python3 tic_tac_toe.py
```

- Player 1 is `X`, Player 2 is `O`.
- Players take turns clicking a square on the 3x3 board.
- First player to get 3 in a row (horizontally, vertically, or diagonally) wins.
- If all 9 squares fill up with no winner, it's a tie.
- Click "New Game" to reset the board.
