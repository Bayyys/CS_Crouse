# CS50’s Introduction to AI with Python

## 课程简介

- 所属大学：Harvard
- 先修要求：基本概率论 + Python基础
- 编程语言：Python
- 课程难度：🌟🌟🌟
- 预计学时：30 小时

一门非常基础的AI入门课，让人眼前一亮的是12个设计精巧的编程作业，都会用学到的AI知识去实现一个简易的游戏AI，比如用强化学习训练一个Nim游戏的AI，用alpha-beta剪枝去扫雷等等，非常适合新手入门或者大佬休闲。

## 课程资源

- 课程网站：https://cs50.harvard.edu/ai/2020/
- 课程视频：https://cs50.harvard.edu/ai/2020/
- 课程教材：无
- 课程作业：https://cs50.harvard.edu/ai/2020/，12个精巧的编程作业

## 资源汇总

@PKUFlyingPig 在学习这门课中用到的所有资源和作业实现都汇总在 [PKUFlyingPig/cs50_ai - GitHub](https://github.com/PKUFlyingPig/cs50_ai) 中。

## AI(Artificial Intelligence)课程内容

- Search
- Knowledge
- Uncertainty
- Optimization(优化)
- Learning
- Neural Networks
- Language

# Lecture 1: Search

- Agent(代理)
  - 感知其环境并对该环境采取行动的实体
- State(状态)
  - 代理在其环境中的配置
  - Initial state(初始状态)
    - 搜索算法开始的状态
- Actions(行动)
  - 某个状态下做出的选择
  - `Action(s)`

- Transition Model(过渡模型)
  - 描述在任何状态下执行任何适用操作所导致的状态
  - `Results(s, a)`
- State Space(状态空间)
  - 可通过任何操作序列从初始状态访问的所有状态的集合

- Goal Test(目标测试)
  - 确定给定状态是否为目标状态的条件
- Path Cost(路径代价)
  - 与给定路径关联的数值成本

## 搜索问题的解

- Solution(解)
  - Optimal Solution(最优解)
- Node(节点)
  - `state`*状态*
  - `fateher`父节点
  - `action`父节点以访问当前节点的操作
  - `path cost`初始状态到此节点的*path cost路径开销*

**frontier(边界)**

```
do{
	1.if frontier is Null:
		stop;
		No solution;
	2.delete node & consider;
	3.if find goal:
		return solution;
		stop;
	4.else:
		* Expand the node (find all the new nodes that could be reached from this node), and add resulting nodes to the frontier.
		* Add the current node to the explored set.
}
```

### 1. DFS(Depth-First Search)

- First in Last out `FILO`
- Stack

### 2. BFS(Breadth-First Search)

- First in First out `FIFO`
- Queue

### 3. GBFS(Greedy Best-First Search)

-  **informed** search algorithm
- **heuristic function** *h(n)*
  - Maze Problem - Manhattan distance

### 4. A* Search

- *h(n) + g(n)* - (*estimated cost to the goal* + *cost of path until now*)

## * 启发式函数h(n)

- **Admissible** *可接受*，或从不*高估*真实成本，以及
- **Consistent** *一致*，这意味着除了从前一个节点转换到新节点的目标的成本外，到新节点目标的估计路径成本大于或等于到前一个节点目标的估计路径成本。用方程形式表示，如果对于每个节点 n 和后续节点 n'，步长成本*为* c、h（n） ≤ h（n'） *+ c，则 h（**n）* 是一致的。

## 对抗性搜索(Adversarial Search)

### 1. Minimax

- 给定一个状态 *s*

  - 最大化玩家在*产生**最小值（结果，a）最高值*的操作中选择操作*a*。
  - 最小化玩家在操作中选择操作 a，该*操作产生*最小值的*最大值（结果，a））。*

- 函数*最大值（状态）*

  - *v = -∞*

  - 如果*终端（状态）：*

    返回*实用程序（状态）*

  - 对于操作*（状态）*中的*操作*：

    v *= Max（v， Min-Value（Result（state， action）））*

    返回 *v*

- 函数*最小值（状态）：*

  - *v = ∞*

  - 如果*终端（状态）：*

    返回*实用程序（状态）*

  - 对于操作*（状态）*中的*操作*：

    v *= Min（v， Max-Value（Result（state， action）））*

    返回 *v*

### 2. Alpha-Beta Pruning

<img src="https://s2.loli.net/2022/12/07/TqONWMaGp9h3HPm.png" alt="image-20221207214101655" style="zoom: 25%;" />

### 3. 深度限制

