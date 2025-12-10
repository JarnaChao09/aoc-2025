from scipy.optimize import linprog

with open("Day10.txt") as f:
    ls = f.read().strip().split("\n")

def solve2(goal, moves):
    return linprog(
        [1] * len(moves),
        A_eq=[[i in m for m in moves] for i in range(len(goal))],
        b_eq=goal,
        integrality=True
    ).fun

part2 = 0.0
for l in ls:
    _, *buttons, jolts = l.split()
    moves = [set(map(int, button[1:-1].split(","))) for button in buttons]
    counters = tuple(map(int, jolts[1:-1].split(",")))
    part2 += solve2(counters, moves)

print(part2)