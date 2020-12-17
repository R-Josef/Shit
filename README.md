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
#累计减少多少饱食度就会屯一坨屎
poopwhenfoodlost: 20

#累计屯了几坨屎就会憋不住
poopcansave: 4

#不会拉屎的人的白名单
whitelist:
- '0f2aa1aa-809a-49cf-8800-bb519a64a93f'

toiletname: '马桶'

#语言提示
message:
  cantkeep: '§3§l你的肚子咕咕作响, 就快要憋不住奥利给了, 赶紧找个马桶解决一下吧!'
  poop: '§3§l你在地上拉了一坨奥利给, 赶快捡起来吃掉吧!'
  poopontoilet: '§3§l你往马桶里拉了%NUM%坨奥利给, 全部掏出来吃掉吧!'
  nopoopontoilet: '§3§l很遗憾, 你暂时没有奥利给, 消耗一点饱食度再来吧!'
```
