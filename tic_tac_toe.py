import tkinter as tk
from tkinter import messagebox

WIN_LINES = [
    (0, 1, 2), (3, 4, 5), (6, 7, 8),  # rows
    (0, 3, 6), (1, 4, 7), (2, 5, 8),  # columns
    (0, 4, 8), (2, 4, 6),             # diagonals
]

MIN_BOARD_SIDE = 240


class TicTacToe:
    def __init__(self, root):
        self.root = root
        self.root.title("Tic Tac Toe")
        self.root.geometry("400x460")
        self.root.minsize(300, 360)
        self.root.resizable(True, True)

        self.board = [""] * 9
        self.current_player = "X"
        self.game_over = False

        self.status_label = tk.Label(
            root, text="Player X's turn", font=("Helvetica", 16)
        )
        self.status_label.pack(side="top", pady=10)

        # This container fills the remaining space; the square board
        # frame is centered inside it and resized to match on <Configure>.
        self.board_container = tk.Frame(root)
        self.board_container.pack(side="top", fill="both", expand=True)

        self.board_frame = tk.Frame(self.board_container)
        self.board_frame.place(relx=0.5, rely=0.5, anchor="center")
        for i in range(3):
            self.board_frame.grid_rowconfigure(i, weight=1)
            self.board_frame.grid_columnconfigure(i, weight=1)

        self.buttons = []
        for i in range(9):
            button = tk.Button(
                self.board_frame,
                text="",
                font=("Helvetica", 32),
                command=lambda i=i: self.handle_click(i),
            )
            button.grid(row=i // 3, column=i % 3, sticky="nsew")
            self.buttons.append(button)

        self.reset_button = tk.Button(
            root, text="New Game", font=("Helvetica", 12), command=self.reset
        )
        self.reset_button.pack(side="bottom", pady=10)

        self.board_container.bind("<Configure>", self.on_container_resize)

    def on_container_resize(self, event):
        side = max(min(event.width, event.height), MIN_BOARD_SIDE)
        self.board_frame.place(width=side, height=side, relx=0.5, rely=0.5, anchor="center")
        font_size = max(int(side / 3 / 2.2), 12)
        for button in self.buttons:
            button.config(font=("Helvetica", font_size))

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
