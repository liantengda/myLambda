# myLambda :blossom:

## 如何调试

1.首先自己的maven仓库应该正确配置，该有的依赖都有。:blush:

2.然后直接启动项目。:smirk:

3.使用postman等测试软件进行测试就可以了。:satisfied:

## 测试指导索引

**Map---->List**

使用lambda将map集合转换为list集合，PersonController中的list接口，还有ScoreController中的list接口

**根据某属性对list集合中的对象进行分组**

PersonController中的listByGrade，根据年级对人员列表分组

**根据某属性去重，然后将该属性集合返回**

PersonController中的listGrade，根据grade属性去重，然后返回grade集合

**根据某属性去重后，然后返回对象集合**

ScoreController中的listPerson，根据personId属性，取出重复的person，然后返回只带有一个person信息
的score集合

**根据某些属性帅选出符合条件的对象集合**

PersonController中的add，再添加人员的时候，默认如果已经有该id的人或者idCard的人
那么则认为该用户已经存在（实际开发中，不对id进行筛选，此项目由于简化，id可能重复）。

**根据某属性分组后，再求出某些属性的算术值（平均值，加和等等）**

ScoreController中的listAverage和listTotalScore，根据personId进行分组，
然后求出每个人的平均分，和总分。