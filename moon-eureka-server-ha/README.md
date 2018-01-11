+ 启动jar时增加如下参数, 并修改相应配置信息
    + 单节点注册/发现服务
        + --spring.profiles.active=single
    + 集群注册/发现服务
        + --spring.profiles.active=peer1
        + --spring.profiles.active=peer2
