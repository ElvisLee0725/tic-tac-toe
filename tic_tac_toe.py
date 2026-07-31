import tkinter as tk
from tkinter import messagebox

WIN_LINES = [
    (0, 1, 2), (3, 4, 5), (6, 7, 8),  # rows
    (0, 3, 6), (1, 4, 7), (2, 5, 8),  # columns
    (0, 4, 8), (2, 4, 6),             # diagonals
]


class TicTacToe:
    def __init__(self, root):
        self.root = root
        self.root.title("Tic Tac Toe")
        self.root.resizable(False, False)

        self.board = [""] * 9
        self.current_player = "X"
        self.game_over = False

        self.status_label = tk.Label(
            root, text="Player X's turn", font=("Helvetica", 16)
        )
        self.status_label.grid(row=0, column=0, columnspan=3, pady=10)

        self.buttons = []
        for i in range(9):
            button = tk.Button(
                root,
                text="",
                font=("Helvetica", 32),
                width=3,
                height=1,
                command=lambda i=i: self.handle_click(i),
            )
            button.grid(row=1 + i // 3, column=i % 3)
            self.buttons.append(button)

        self.reset_button = tk.Button(
            root, text="New Game", font=("Helvetica", 12), command=self.reset
        )
        self.reset_button.grid(row=4, column=0, columnspan=3, pady=10)

    def handle_click(self, index):
        if self.game_over or self.board[index] != "":
            return

        self.board[index] = self.current_player
        self.buttons[index].config(text=self.current_player)

        winning_line = self.check_winner()
        if winning_line:
            self.game_over = True
            self.highlight_winner(winning_line)
            self.status_label.config(text=f"Player {self.current_player} wins!")
            messagebox.showinfo("Game Over", f"Player {self.current_player} wins!")
        elif "" not in self.board:
            self.game_over = True
            self.status_label.config(text="It's a tie!")
            messagebox.showinfo("Game Over", "It's a tie!")
        else:
            self.current_player = "O" if self.current_player == "X" else "X"
            self.status_label.config(text=f"Player {self.current_player}'s turn")

    def check_winner(self):
        for a, b, c in WIN_LINES:
            if self.board[a] and self.board[a] == self.board[b] == self.board[c]:
                return (a, b, c)
        return None

    def highlight_winner(self, line):
        for i in line:
            self.buttons[i].config(bg="lightgreen")

    def reset(self):
        self.board = [""] * 9
        self.current_player = "X"
        self.game_over = False
        self.status_label.config(text="Player X's turn")
        for button in self.buttons:
            button.config(text="", bg="SystemButtonFace")


def main():
    root = tk.Tk()
    TicTacToe(root)
    root.mainloop()


if __name__ == "__main__":
    main()
