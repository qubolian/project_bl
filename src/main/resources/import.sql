INSERT INTO user (id, user_name, password, name, email) VALUES (1, 'admin', '123456', '张嗣峰', 'i@sina.com');
INSERT INTO user (id, user_name, password, name, email)  VALUES (2, 'magic', '123456', 'magic', 'magic@126.com');

INSERT INTO authority (id, name,mark) VALUES (1, 'ROLE_ADMIN','系统管理员');
INSERT INTO authority (id, name,mark) VALUES (6, 'ROLE_USER','辅导员');
INSERT INTO authority (id, name,mark) VALUES (3, 'ROLE_TEACHER','教师');
INSERT INTO authority (id, name,mark) VALUES (2, 'ROLE_SUPER','院办管理员');
INSERT INTO authority (id, name,mark) VALUES (4, 'ROLE_STUDENT','学生');
INSERT INTO authority (id, name,mark) VALUES (5, 'ROLE_DIRECTOR','系主任');
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO user_authority (user_id, authority_id) VALUES (2, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 3);
INSERT INTO user_authority (user_id, authority_id) VALUES (1, 5);


INSERT INTO project_mission (id, mark,misson) VALUES (1, '这是个宗旨的内容','测试一下');

insert into Department_List(Code,Department) values(080901,'计算器科学与技术系');
insert into Department_List(Code,Department) values(080902,'软件工程系');
insert into Department_List(Code,Department) values(080903,'网络工程系');
insert into Department_List(Code,Department) values(080905,'物联网工程系');
insert into Department_List(Code,Department) values(080906,'数字媒体技术系');
insert into News_Type(Message_Type) values('竞赛消息（contest）');
insert into News_Type(Message_Type) values('得奖消息（Match message）');
insert into News_Type(Message_Type) values('业界&学校交流（Discuss）');





INSERT INTO WHATS_NEW VALUES ('1', '2012-12-01', '几天呢他', 'note', '2012-11-01', '3', '2');
INSERT INTO WHATS_NEW VALUES ('2', '2013-12-01', '商谈信息', 'note', '2013-11-01', '3', '2');
INSERT INTO WHATS_NEW VALUES ('3', '2014-12-01', '掉头就像', 'note', '2014-11-01', '3', '2');

INSERT INTO Howto_Team_Up(id,Grade_ID,Team_Up_Stage,Seek_Advisor_Stage,Project_Conduction_Stage)  VALUES('1', '大一上', '开学前2 Weeks', '开学前3 Weeks', '整学期');
INSERT INTO Howto_Team_Up(id,Grade_ID,Team_Up_Stage,Seek_Advisor_Stage,Project_Conduction_Stage)  VALUES('2', '大一下', '开学前2 Weeks', '开学前3 Weeks', '整学期');
INSERT INTO Howto_Team_Up(id,Grade_ID,Team_Up_Stage,Seek_Advisor_Stage,Project_Conduction_Stage)  VALUES('3', '大二上', '开学前2 Weeks', '开学前3 Weeks', '整学期');
INSERT INTO Howto_Team_Up(id,Grade_ID,Team_Up_Stage,Seek_Advisor_Stage,Project_Conduction_Stage)  VALUES('4', '大二下', '开学前2 Weeks', '开学前3 Weeks', '整学期');
INSERT INTO Howto_Team_Up(id,Grade_ID,Team_Up_Stage,Seek_Advisor_Stage,Project_Conduction_Stage)  VALUES('5', '大三上', '不得变更团队', '不得变更指导老师', '整学期');



insert into leader_member_responsibility(Leader_Responsibility,Member_Responsibility) values('专题档案管理&规划，开会讨论题目，定期问题讨论及询问进度，制定时间&缴交作业，分工管理，成本计算，与老师的沟通&组员','将档案整理好给组长，提出有兴趣的题目，向组长回报问题与进度，在时间内缴交作业，执行被指派的任务，提出所需的物品&价格，良好的沟通行为');


INSERT INTO Teacher (Id, Teacher_Name, Department, Sex, Teacher_Type, College)  VALUES (04032, '赵钱孙李', '计算器科学与技术系', '男', '专任教师', '信息技术学院');
INSERT INTO Teacher (Id, Teacher_Name, Department, Sex, Teacher_Type, College)  VALUES (04033, '周吴郑王', '计算器科学与技术系', '女', '教辅人员', '信息技术学院');
INSERT INTO Teacher (Id, Teacher_Name, Department, Sex, Teacher_Type, College)  VALUES (04034, '斯蒂芬', '计算器科学与技术系', '女', '兼任（职）教师', '信息技术学院');
INSERT INTO Teacher (Id, Teacher_Name, Department, Sex, Teacher_Type, College)  VALUES (04035, '阿斯顿', '计算器科学与技术系', '女', '行政人员', '信息技术学院');
INSERT INTO Teacher (Id, Teacher_Name, Department, Sex, Teacher_Type, College)  VALUES (04036, '周星驰', '计算器科学与技术系', '男', '专任教师', '信息技术学院');


INSERT INTO Course(id, course_Id, credit, classes, course_Type, name, term, course_Number, exam_Methods, period) VALUES (1, '1', '2', '计科B17-1', '专业领域课程组', '英语', '2017-2018-1', '2050587', '考试', '32');
INSERT INTO Course(id, course_Id, credit, classes, course_Type, name, term, course_Number, exam_Methods, period) VALUES (2, '2', '4', '计科B17-1', '必修', '数学', '2017-2018-1', '2050588', '考查', '32');
INSERT INTO Course(id, course_Id, credit, classes, course_Type, name, term, course_Number, exam_Methods, period) VALUES (3, '3', '3', '计科B17-1', '集中实践', '软件开发', '2017-2018-1', '2050589', '考查', '32');
INSERT INTO Course(id, course_Id, credit, classes, course_Type, name, term, course_Number, exam_Methods, period) VALUES (4, '4', '1', '计科B17-1', '专业限选课', '界面制作', '2017-2018-1', '2050590', '考查', '32');
INSERT INTO Course(id, course_Id, credit, classes, course_Type, name, term, course_Number, exam_Methods, period) VALUES (5, '5', '2', '计科B17-1', '选修', '语文', '2017-2018-1', '2050591', '考查', '32');

INSERT INTO student (Id, Name, Department, Sex, grade, Length_Of_Schooling, Training_Level, Major, Entrance_Time, Status, Classes) VALUES (1623381, '张三', '计算器科学与技术系', '4', '2016-9', '男', '本科', '计算机科学与技术', '2016-09-01', '在籍', '计科B17-1');
INSERT INTO student (Id, Name, Department, Sex, grade, Length_Of_Schooling, Training_Level, Major, Entrance_Time, Status, Classes) VALUES (1623382, '刘一', '计算器科学与技术系', '4', '2016-9', '男', '本科', '计算机科学与技术', '2016-09-01', '在籍', '计科B17-1');
INSERT INTO student (Id, Name, Department, Sex, grade, Length_Of_Schooling, Training_Level, Major, Entrance_Time, Status, Classes) VALUES (1623383, '陈二', '计算器科学与技术系', '4', '2016-9', '男', '本科', '计算机科学与技术', '2016-09-01', '在籍', '计科B17-1');
INSERT INTO student (Id, Name, Department, Sex, grade, Length_Of_Schooling, Training_Level, Major, Entrance_Time, Status, Classes) VALUES (1623384, '李四', '计算器科学与技术系', '4', '2016-9', '男', '本科', '计算机科学与技术', '2016-09-01', '在籍', '计科B17-1');
INSERT INTO student (Id, Name, Department, Sex, grade, Length_Of_Schooling, Training_Level, Major, Entrance_Time, Status, Classes) VALUES (1623385, '王五', '计算器科学与技术系', '4', '2016-9', '男', '本科', '计算机科学与技术', '2016-09-01', '在籍', '计科B17-1');






