# Write your MySQL query statement below
select FirstName, LastName, City, State
from Person left join Address
on Person.PersonId = Address.PersonId;
#   1）注意审题：
#       题目说：无论 person 是否有地址信息，都需要基于上述两表提供 person 的以下信息： FirstName, LastName, City, State
#       也就是说，地址信息（City, State）的查询结果是Null是OK的。但是，姓名（FirstName, LastName）必须有。

# ?2） 为啥不用Where？
#       因为where的实质就是根据你给的条件（personID相等），选取两表的公共部分。
#       但是，因为PERSON表不是所有人都有地址信息的，但是ADDRESS表只显示有地址信息的人，这样选取出来的就是有地址信息的人，
#       漏掉了没有地址信息的人。所以大家注意，where的本质就是过滤。

#  3） 如何连接？
#       应该用PERSON表左连接（left join）ADDRESS表，保留person表的所有信息