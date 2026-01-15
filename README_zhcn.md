# Shit

让玩家们在游戏里拉史，需要ItemsAdder才能使用。

## 特性

- 史可以赤
- 史可以做出来
- 玩家在损失饥饿值的时候会拉史
- 总共4个新物品: 屎/蛋筒/屎冰激凌/屎曲奇

## 如何使用

此插件必须先安装[ItemsAdder](https://www.spigotmc.org/resources/%E2%9C%85must-have%E2%9C%85-itemsadder%E2%9C%A8custom-items-huds-guis-textures-3dmodels-emojis-blocks-wings-hats.73355/)才能运行。

1. 下载[最新发布版](https://github.com/R-Josef/Shit/releases)然后解压。
2. 将插件文件(Shit-xxx.jar)放进`plugins`文件夹。
3. 将`contents`文件夹里的东西移动进ItemsAdder的`contents`文件夹里
4. 启动服务器并执行`/iazip`指令，生成新的资源包。
5. 现在你可以加载资源包，它在`plugins/ItemsAdder/output/generated.zip`目录下。如果你在IA中设置了自托管，那么无需手动加载。

## 配置文件

```yaml
#语言设置
#Language setting
lang: en_US

#累计减少多少饱食度就会屯一坨屎
#Decide how many food levels when a player lost, a shit will stored in this player's belly
poopwhenfoodlost: 20

#累计屯了几坨屎就会憋不住
#Decide how many shit can store in player's belly
poopcansave: 4

#不会拉屎的人的白名单
#White list of people who can not shit
whitelist:
- '0f2aa1aa-809a-49cf-8800-bb519a64a93f'

#马桶的完整自定义ID (用于判断是否为马桶, 建议不要修改)
#The complete custom ID of the toilet
#(Used to determine whether it is a toilet, it is not recommended to modify)
toiletid: 'shit:toilet'

#屎的完整自定义ID (用于生成屎, 建议不要修改)
#The complete custom ID of the shit
#(Used to generate shit, it is not recommended to modify)
shitid: 'shit:shit'
```

## 关于马桶

马桶的模型不是我创建的，它来自[MrCrayfishFurnitureMod](https://github.com/MrCrayfish/MrCrayfishFurnitureMod)，该项目是[GNU General Public License](https://github.com/MrCrayfish/MrCrayfishFurnitureMod/blob/1.15.X/LICENSE)许可的，作者是[MrCrayfish](https://github.com/MrCrayfish)。

在此基础上，Asterism和[Shimamura Tako](https://github.com/Shimamura-Tako)对其进行了修改，以适应原版 Minecraft 客户端，并让显示效果更佳。
