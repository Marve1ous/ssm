# SSM
## Spring + Spring MVC + Mybatis + Shiro

###学习成果
jsp接收表单的参数为name属性
```
<input name="id">
RequestParam <- formdata
RequestBody Map <- json
```

mybatis绑定数据 @Param
```
    UserAccount selectById(@Param("id") String id);
```