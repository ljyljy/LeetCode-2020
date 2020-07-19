# # Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.Return -1 if knight can not reached.
# # Notice:
# # source and destination must be empty.
# # Knight can not enter the barrier.
# # Clarification：
# # If the knight is at (x, y), he can get to the following positions in one step:
# # (x + 1, y + 2)
# # (x + 1, y - 2)
# # (x - 1, y + 2)
# # (x - 1, y - 2)
# # (x + 2, y + 1)
# # (x + 2, y - 1)
# # (x - 2, y + 1)
# # (x - 2, y - 1)
# # Example
# # [[0,0,0],
# #  [0,0,0],
# #  [0,0,0]]
# # source = [2, 0] destination = [2, 2] return 2
# #
# # [[0,1,0],
# #  [0,0,0],
# #  [0,0,0]]
# # source = [2, 0] destination = [2, 2] return 6
# #
# # [[0,1,0],
# #  [0,0,1],
# #  [0,0,0]]
# # source = [2, 0] destination = [2, 2] return -1
# # follow up: speed up?  ——使用两个Deque从头尾同时遍历，可以减少一半时间
# # 注意:
# # 思路就是用BFS一层一层搜，但要记得搜过的点要标识为1，不然会陷入死循环-¬-走成环。
# # 记得每次从queue中取出节点就要判断是不是终点，而不是放在下面一个8方向遍历的循环里面判断。
# from collections import deque
# from typing import List
#
#
# class Point:
# 	def __init__(self, x=0, y=0):
# 		self.x = x
# 		self.y = y
#
# class Solution:
# 	def shortestPath(self, grid: List[List[bool]], \
# 					 src: Point, dest: Point) -> int:
# 		m, n = len(grid), len(grid[0])
# 		if m == 0 or n == 0: return -1
# 		directions = [(-1, 2), (2, -1), (1, 2), (2, 1),\
# 					  (1, -2), (-2, 1), (-1, -2), (-2, -1)]
# 		queue = deque([src])
# 		grid[src.x][src.y] = True
# 		step = 0
# 		while queue:
# 			for _ in range(len(queue)):
# 				cur = queue.popleft()
# 				if cur.x == dest.x and cur.y == dest.y:
# 					return step
# 				for i in range(0, 8):
# 					new_x = cur.x + directions[i][0]
# 					new_y = cur.y + directions[i][1]
# 					point = Point(new_x, new_y)
# 					if _inBound(point, grid) and not grid[new_x][new_y]:
# 						grid[new_x][new_y] = True
# 						queue.append(point)
# 			step += 1  #  count 可以理解为连通分量，你可以在广度优先遍历完成以后，再计数
#
#
# def _inBound(p, grid):
# 	if grid[p.x][p.y] and 0 <= p.x < len(grid) \
# 		and 0 <= p.y < len(grid[0]):
# 		return True
# 	return False
