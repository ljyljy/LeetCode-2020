# Write your MySQL query statement below
select FirstName, LastName, City, State
from Person left join Address
on Person.PersonId = Address.PersonId;
#   1��ע�����⣺
#       ��Ŀ˵������ person �Ƿ��е�ַ��Ϣ������Ҫ�������������ṩ person ��������Ϣ�� FirstName, LastName, City, State
#       Ҳ����˵����ַ��Ϣ��City, State���Ĳ�ѯ�����Null��OK�ġ����ǣ�������FirstName, LastName�������С�

# ?2�� Ϊɶ����Where��
#       ��Ϊwhere��ʵ�ʾ��Ǹ��������������personID��ȣ���ѡȡ����Ĺ������֡�
#       ���ǣ���ΪPERSON���������˶��е�ַ��Ϣ�ģ�����ADDRESS��ֻ��ʾ�е�ַ��Ϣ���ˣ�����ѡȡ�����ľ����е�ַ��Ϣ���ˣ�
#       ©����û�е�ַ��Ϣ���ˡ����Դ��ע�⣬where�ı��ʾ��ǹ��ˡ�

#  3�� ������ӣ�
#       Ӧ����PERSON�������ӣ�left join��ADDRESS������person���������Ϣ