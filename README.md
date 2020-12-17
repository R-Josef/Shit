# Shit

Make player poop in game, en addon for bukkit plugin ItemsAdder.

## Features

- Shit is eatable

- Shit is createable

- Player will poop when he/she lost his/her food-level

- Total 4 new item: shit/cone/shit_cone/shit_cookie

## How to use

This plugin is require on [ItemsAdder](https://www.spigotmc.org/resources/%E2%9C%85must-have%E2%9C%85-itemsadder%E2%9C%A8custom-items-huds-guis-textures-3dmodels-emojis-blocks-wings-hats.73355/), you must Install ItemsAdder on your server at frist.

1. Download the [latest release](https://github.com/R-Josef/Shit/releases) and unzip it.

2. Put this plugin(Shit-xxx.jar) into `plugins` folder.

3. Copy the data folder whitch you just unziped into ItemsAdder folder.

4. In order to render a new resource pack whitch is includ config and texture of this new item "shit", you should use `iazip` command in server console.

5. Now you can use the new resource pack, it's at `plugins/ItemsAdder/data/resource_pack/pack.zip` . You don't have to do anything if you use self host.

## Config file

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
