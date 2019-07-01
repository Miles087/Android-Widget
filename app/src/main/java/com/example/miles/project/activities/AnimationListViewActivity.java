package com.example.miles.project.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miles.project.R;
import com.example.miles.project.adapter.AnimListCell;
import com.example.miles.project.adapter.ItemAnimListCellAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Server on 2018/4/9.
 */

@RequiresApi(api = Build.VERSION_CODES.M)
public class AnimationListViewActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener, AbsListView.OnScrollListener {

    private ListView lv_list = null;
    private LinearLayout ll_mask = null;
    private Context mContext = null;
    private Button btn_cummit = null;
    private TextView tv_more_text = null;
    private List<AnimListCell> list = null;
    private int iOffSite = 1;           //被选中listView的位置
    private ItemAnimListCellAdapter adapter = null;
    private boolean hasScroll = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim_listview);
        mContext = this;
        lv_list = (ListView)findViewById(R.id.lv_list);
        ll_mask = (LinearLayout)findViewById(R.id.ll_mask);
        btn_cummit = (Button) findViewById(R.id.btn_cummit);
        tv_more_text = (TextView) findViewById(R.id.tv_more_text);

        lv_list.setOnScrollListener(this);
        lv_list.setOnItemClickListener(this);
        btn_cummit.setOnClickListener(this);
    }

    private void initListData(){
        AnimListCell cell1 = new AnimListCell();
        cell1.setStrTime("借我");
        cell1.setStrContent("谢春花");
        cell1.setStrText("作曲 : 谢知非\n" +
                "\n" +
                "作词 : 谢知非/锦屏\n" +
                "\n" +
                "编曲/混音：小皮\n" +
                "\n" +
                "吉他 : 卢山/小皮\n" +
                "\n" +
                "借我十年\n" +
                "\n" +
                "借我亡命天涯的勇敢\n" +
                "\n" +
                "借我说得出口的旦旦誓言\n" +
                "\n" +
                "借我孤绝如初见\n" +
                "\n" +
                "借我不惧碾压的鲜活\n" +
                "\n" +
                "借我生猛与莽撞不问明天\n" +
                "\n" +
                "借我一束光照亮黯淡\n" +
                "\n" +
                "借我笑颜灿烂如春天\n" +
                "\n" +
                "借我杀死庸碌的情怀\n" +
                "\n" +
                "借我纵容的悲怆与哭喊\n" +
                "\n" +
                "借我怦然心动如往昔\n" +
                "\n" +
                "借我安适的清晨与傍晚\n" +
                "\n" +
                "静看光阴荏苒\n" +
                "\n" +
                "借我喑哑无言\n" +
                "\n" +
                "不管不顾不问不说\n" +
                "\n" +
                "也不念\n" +
                "\n" +
                "静看光阴荏苒\n" +
                "\n" +
                "借我喑哑无言\n" +
                "\n" +
                "不管不顾不问不说\n" +
                "\n" +
                "也不念\n" +
                "\n" +
                "借我十年\n" +
                "\n" +
                "借我亡命天涯的勇敢\n" +
                "\n" +
                "借我说得出口的旦旦誓言\n" +
                "\n" +
                "借我孤绝如初见\n" +
                "\n" +
                "借我不惧碾压的鲜活\n" +
                "\n" +
                "借我生猛与莽撞不问明天\n" +
                "\n" +
                "借我一束光照亮黯淡\n" +
                "\n" +
                "借我笑颜灿烂如春天\n" +
                "\n" +
                "借我杀死庸碌的情怀\n" +
                "\n" +
                "借我纵容的悲怆与哭喊\n" +
                "\n" +
                "借我怦然心动如往昔\n" +
                "\n" +
                "借我安适的清晨与傍晚\n" +
                "\n" +
                "静看光阴荏苒\n" +
                "\n" +
                "借我喑哑无言\n" +
                "\n" +
                "不管不顾不问不说\n" +
                "\n" +
                "也不念\n" +
                "\n" +
                "静看光阴荏苒\n" +
                "\n" +
                "借我喑哑无言\n" +
                "\n" +
                "不管不顾不问不说\n" +
                "\n" +
                "也不念");
        list.add(cell1);
        AnimListCell cell2 = new AnimListCell();
        cell2.setStrTime("なんでもないや");
        cell2.setStrContent("Akie秋绘");
        cell2.setStrText("二人の間 通り過ぎた風は\n" +
                "どこから寂しさを運んできたの\n" +
                "泣いたりしたそのあとの空は\n" +
                "やけに透き通っていたりしたんだ\n" +
                "いつもは尖ってた父の言葉が\n" +
                "今日は暖かく感じました\n" +
                "優しさも笑顔も夢の語り方も\n" +
                "知らなくて全部 君を真似たよ\n" +
                "もう少しだけでいい あと少しだけでいい\n" +
                "もう少しだけでいいから\n" +
                "もう少しだけでいい あと少しだけでいい\n" +
                "もう少しだけ くっついていようか\n" +
                "僕らタイムフライヤー 時を駆け上がるクライマー\n" +
                "時のかくれんぼ はぐれっこはもういやなんだ\n" +
                "嬉しくて泣くのは 悲しくて笑うのは\n" +
                "君の心が 君を追い越したんだよ\n" +
                "星にまで願って 手にいれたオモチャも\n" +
                "部屋の隅っこに今 転がってる\n" +
                "叶えたい夢も 今日で100個できたよ\n" +
                "たった一つといつか 交換こしよう\n" +
                "いつもは喋らないあの子に今日は\n" +
                "放課後「また明日」と声をかけた\n" +
                "慣れないこともたまにならいいね\n" +
                "特にあなたが 隣にいたら\n" +
                "もう少しだけでいい あと少しだけでいい\n" +
                "もう少しだけでいいから\n" +
                "もう少しだけでいい あと少しだけでいい\n" +
                "もう少しだけくっついていようよ\n" +
                "僕らタイムフライヤー 君を知っていたんだ\n" +
                "僕が 僕の名前を 覚えるよりずっと前に\n" +
                "君のいない 世界にも 何かの意味はきっとあって\n" +
                "でも君のいない 世界など 夏休みのない 八月のよう\n" +
                "君のいない 世界など 笑うことない サンタのよう\n" +
                "君のいない 世界など\n" +
                "僕らタイムフライヤー 時を駆け上がるクライマー\n" +
                "時のかくれんぼ はぐれっこはもういやなんだ\n" +
                "なんでもないや やっぱりなんでもないや\n" +
                "今から行くよ\n" +
                "僕らタイムフライヤー 時を駆け上がるクライマー\n" +
                "時のかくれんぼ はぐれっこ はもういいよ\n" +
                "君は派手なクライヤー その涙 止めてみたいな\n" +
                "だけど 君は拒んだ 零れるままの涙を見てわかった\n" +
                "嬉しくて泣くのは 悲しくて 笑うのは\n" +
                "僕の心が 僕を追い越したんだよ");
        list.add(cell2);
        AnimListCell cell3 = new AnimListCell();
        cell3.setStrTime("我开始摇滚了");
        cell3.setStrContent("正午阳光");
        cell3.setStrText("我没钱没地位\n" +
                "\n" +
                "因为我爸没职位\n" +
                "\n" +
                "我爸不可能受贿\n" +
                "\n" +
                "我也不惭愧\n" +
                "\n" +
                "我从小很刻苦\n" +
                "\n" +
                "可学的东西没用处\n" +
                "\n" +
                "我学了十年算算术\n" +
                "\n" +
                "他最后让我喂猪\n" +
                "\n" +
                "你给我指了条路\n" +
                "\n" +
                "叫我替你扛包袱\n" +
                "\n" +
                "说活着就得装糊涂\n" +
                "\n" +
                "要不然就开除\n" +
                "\n" +
                "我不想装糊涂\n" +
                "\n" +
                "想找个地方吐\n" +
                "\n" +
                "我浑身都是嘴却挨了你一腿\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我先留头发再剃个秃子\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "谁借我两钱我买把吉它\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我喝点小酒再找点想法\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我摇不摇滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "呸\n" +
                "\n" +
                " \n" +
                "\n" +
                "我没钱没地位\n" +
                "\n" +
                "因为我爸没职位\n" +
                "\n" +
                "我爸爸不可能受贿\n" +
                "\n" +
                "我也不惭愧\n" +
                "\n" +
                "我从小很刻苦\n" +
                "\n" +
                "可学的东西没用处\n" +
                "\n" +
                "我学了十年算算术\n" +
                "\n" +
                "他最后让我喂猪\n" +
                "\n" +
                "你给我指了条路\n" +
                "\n" +
                "叫我替你扛包袱\n" +
                "\n" +
                "说活着就得装糊涂\n" +
                "\n" +
                "要不然就开除\n" +
                "\n" +
                "我不想装糊涂\n" +
                "\n" +
                "想找个地方吐\n" +
                "\n" +
                "我浑身都是嘴却挨了你一腿。\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我先留头发再剃个秃子\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "谁借我两钱我买把吉它\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我喝点小酒我找点想法\n" +
                "\n" +
                "我开始摇滚了\n" +
                "\n" +
                "我摇不摇滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "我要你滚\n" +
                "\n" +
                "滚\n" +
                "\n");
        list.add(cell3);
        AnimListCell cell4 = new AnimListCell();
        cell4.setStrTime("J'En Ai Marre");
        cell4.setStrContent("Alizée");
        cell4.setStrText("Jai la peau douce\n" +
                "Dans mon bain de mousse\n" +
                "Je méclabousse\n" +
                "Jen ris ...\n" +
                "Mon poisson bouge\n" +
                "Dans mon bain de mousse\n" +
                "Je lemmitoufle, je\n" +
                "Lui dis\n" +
                "Jai pas de problèmes, je fainéante...\n" +
                "Pas de malaises, je fainéante\n" +
                "Dans leau je baigne, cest limportant\n" +
                "Bien à mon aise, dans lair du temps\n" +
                "Jai la peau douce\n" +
                "Dans mon bain de mousse\n" +
                "Je brule à lombre\n" +
                "Des bombes\n" +
                "Tout est délice\n" +
                "Délit docile :\n" +
                "Je fais la liste\n" +
                "Des choses...qui\n" +
                "Mindisposent\n" +
                "Jen ai marre de ceux qui pleurent.\n" +
                "Qui ne roulent quà deuxà lheure,\n" +
                "Qui se lamentent et qui sfixent\n" +
                "Sur lidée dune idée fixe\n" +
                "Jen ai marre de ceux qui ralent.\n" +
                "Des extrêmistes à deux balles,\n" +
                "Qui voient la vie tout en noir\n" +
                "Qui mexpédient dans lcafard\n" +
                "Jen ai marre de la grande soeur,\n" +
                "Qui gémit tout et qui pleure,\n" +
                "Marre de la pluie, des courgettes\n" +
                "Qui mfont vomir sous la couette\n" +
                "Jen ai marre de ces cyniques,\n" +
                "Et dans les prés, les colchiques,\n" +
                "Jen ai marre den avoir marre ! Aussi\n" +
                "Jai la peau douce\n" +
                "Dans mon bain de mousse\n" +
                "Pas de secousses\n" +
                "Sismiques...\n" +
                "Je me prélasse\n" +
                "Et me délasse\n" +
                "Cest mon état aquatique\n" +
                "Ya comme un hic\n" +
                "Jen ai marre de ceux qui pleurent.\n" +
                "Qui ne roulent quà deuxà lheure,\n" +
                "Qui se lamentent et qui sfixent\n" +
                "Sur lidée dune idée fixe\n" +
                "Jen ai marre de ceux qui ralent.\n" +
                "Des extrêmistes à deux balles,\n" +
                "Qui voient la vie tout en noir\n" +
                "Qui mexpédient dans lcafard\n" +
                "Jen ai marre de la grande soeur,\n" +
                "Qui gémit tout et qui pleure,\n" +
                "Marre de la pluie, des courgettes\n" +
                "Qui mfont vomir sous la couette\n" +
                "Jen ai marre de ces cyniques,\n" +
                "Et dans les prés, les colchiques,\n" +
                "Jen ai marre den avoir marre ! Aussi\n" +
                "Jen ai marre de ceux qui pleurent.\n" +
                "Qui ne roulent quà deuxà lheure,\n" +
                "Qui se lamentent et qui sfixent\n" +
                "Sur lidée dune idée fixe\n" +
                "Jen ai marre de ceux qui ralent.\n" +
                "Des extrêmistes à deux balles,\n" +
                "Qui voient la vie tout en noir\n" +
                "Qui mexpédient dans lcafard\n" +
                "Jen ai marre de la grande soeur,\n" +
                "Qui gémit tout et qui pleure,\n" +
                "Marre de la pluie, des courgettes\n" +
                "Qui mfont vomir sous la couette\n" +
                "Jen ai marre de ces cyniques,\n" +
                "Et dans les prés, les colchiques,\n" +
                "Jen ai marre den avoir marre !");
        list.add(cell4);
        AnimListCell cell5 = new AnimListCell();
        cell5.setStrTime("世界は恋に落ちている");
        cell5.setStrContent("CHiCO with HoneyWorks");
        cell5.setStrText("世界は恋に落ちている\n" +
                "我的世界已坠入爱河\n" +
                "光の矢 胸を射す\n" +
                "闪光的箭 刺穿我的心脏\n" +
                "君をわかりたいんだよ\n" +
                "好想要了解你的全部\n" +
                "「ねえ、教えて」\n" +
                "「呐，告诉我」\n" +
                "すれ違う言葉にちょっとだけの後悔\n" +
                "对那擦肩而过的话语略有些后悔\n" +
                "涙こぼれて\n" +
                "泪水零落\n" +
                "忙しい感情 鼓動にリンクする\n" +
                "将那忙碌的感情和心跳相连\n" +
                "チューニング\n" +
                "调和旋律\n" +
                "確かめたいんだ\n" +
                "好想得到确认啊\n" +
                "目的ばっかにとらわれて\n" +
                "被目的所拘束\n" +
                "大事なものが霞んで逃げて\n" +
                "重要的事却模糊淡去\n" +
                "今日もリスタート\n" +
                "今天又将是崭新的一天\n" +
                "世界は恋に落ちている\n" +
                "我的世界已坠入爱河\n" +
                "光の矢 胸を射す\n" +
                "闪光的箭 刺穿我的心脏\n" +
                "全部わかりたいんだよ\n" +
                "好想要了解你的全部\n" +
                "「ねえ、聞かせて」\n" +
                "「呐，让我听听吧」\n" +
                "たった1ミリが遠くて\n" +
                "即使一毫米也太过遥远\n" +
                "駆け抜けた青春（ひび）に\n" +
                "这呼啸而过的青春\n" +
                "忘れない忘れられない\n" +
                "不想忘记也无法忘记\n" +
                "輝く 1ページ\n" +
                "那闪耀着光芒的一页\n" +
                "お似合いの二人になんだか複雑な\n" +
                "那看起来很般配的两人之间\n" +
                "気持ちがいるよ\n" +
                "似乎有着复杂的感情\n" +
                "初めての感情 鼓動にリンクする\n" +
                "将那初萌的感情和心跳相连\n" +
                "体温計\n" +
                "体温计\n" +
                "壊れちゃったかな？\n" +
                "是不是坏了呢？\n" +
                "自分のこと分からないまま\n" +
                "连自己的心情都不了解\n" +
                "あの子にアドバイスまでしちゃって\n" +
                "却向她提供了建议\n" +
                "胸が痛いや…\n" +
                "心脏隐隐作痛\n" +
                "世界は恋に落ちている\n" +
                "我的世界已坠入爱河\n" +
                "光の矢 胸を射す\n" +
                "闪光的箭 刺穿我的心脏\n" +
                "気付いたこの想いは\n" +
                "终于注意到的这份感情\n" +
                "「もう、遅いの」\n" +
                "「已经，来不及了」\n" +
                "あの子の方がかわいいの\n" +
                "她比起我更加动人\n" +
                "知ってるよだけど\n" +
                "我明明知道这点\n" +
                "「うまくいかないで」なんてね…\n" +
                "「不要进行得太顺利啊」之类的想法……\n" +
                "逃げ出したくせに\n" +
                "已经逃离的我又何必\n" +
                "バカ…\n" +
                "笨蛋！\n" +
                "君の事全部分かりたいって思っちゃう\n" +
                "好想了解你的全部啊\n" +
                "これが恋だって初めて気づいたの\n" +
                "我才注意到这就是恋爱\n" +
                "絶対振り向いてほしい\n" +
                "请一定回头看看我\n" +
                "遅かったけど諦めたくない\n" +
                "虽然有些迟了，但是我不想放弃\n" +
                "だって\n" +
                "因为……\n" +
                "好きだから\n" +
                "我喜欢你\n" +
                "春に咲いた花が恋をした\n" +
                "春天盛开的花朵恋爱了\n" +
                "花は必死に上を向いて笑った\n" +
                "它们竭力抬起头，绽开了笑容\n" +
                "青い夏の蕾も恋をした\n" +
                "夏天青涩的花蕾也恋爱了\n" +
                "咲かない花と火薬の匂い\n" +
                "无法开放的花，与火药的气息\n" +
                "ホントの気持ち言葉にして\n" +
                "将真心的感情化为语言\n" +
                "大事なこと話せたら\n" +
                "将重要的事说出来的话\n" +
                "今日もリスタート\n" +
                "今天又将是崭新的一天\n" +
                "鈍感な君だから\n" +
                "正因为你有些迟钝\n" +
                "口に出して言わなきゃ\n" +
                "所以我一定要亲口传达\n" +
                "今 君に伝えるよ\n" +
                "现在，我就要告诉你\n" +
                "「ねえ、好きです」\n" +
                "「呐，我喜欢你」\n" +
                "世界は恋に落ちている\n" +
                "我的世界已坠入爱河\n" +
                "光の矢 胸を射す\n" +
                "闪光的箭 刺穿我的心脏\n" +
                "全部わかりたいんだよ\n" +
                "好想要了解你的全部\n" +
                "「ねえ、聞かせて」\n" +
                "「呐，让我听听吧」\n" +
                "手繰り寄せてもう０センチ\n" +
                "用力将你拉近至零距离\n" +
                "駆け抜けた青春（ひび）に\n" +
                "这呼啸而过的青春\n" +
                "忘れない忘れられない\n" +
                "不想忘记也无法忘记\n" +
                "輝く 1ページ\n" +
                "那闪耀着光芒的一页");
        list.add(cell5);
        AnimListCell cell6 = new AnimListCell();
        cell6.setStrTime("生来倔强");
        cell6.setStrContent("南征北战");
        cell6.setStrText("有一种力量无人能抵挡\n" +
                "它永不言败生来倔强\n" +
                "有一种理想照亮了迷茫\n" +
                "在那写满荣耀的地方\n" +
                "当那熟悉的旋律响起\n" +
                "昂首挺胸\n" +
                "仰望那鲜艳的旗帜扬起\n" +
                "灾难降临时你我患难与共\n" +
                "互助 关爱 绝不无动于衷\n" +
                "与真相对白\n" +
                "正视历史勇敢面对未来\n" +
                "期盼着英雄们平安地归来\n" +
                "从不妥协 大国尊严不容侵犯\n" +
                "永不让步 每寸领土不容侵占\n" +
                "我不再是从前那饱受欺辱的模样\n" +
                "坚毅的心和脚步没有谁能够阻挡\n" +
                "我不在乎别人是否对我嘲笑\n" +
                "尽管曾经我也是那样的渺小\n" +
                "乘风破浪就像踏步在长征的路上\n" +
                "誓言捍卫这片大地利剑般的目光\n" +
                "我为我的祖国而感到骄傲\n" +
                "就让五星红旗自由地飘\n" +
                "有一种力量无人能抵挡\n" +
                "它永不言败生来倔强\n" +
                "有一种理想照亮了迷茫\n" +
                "在我深感自豪的地方\n" +
                "因为我是这个国家的青年\n" +
                "坚信着都有一个共同的心愿\n" +
                "经历了贫穷和落后也曾一无所有\n" +
                "义无反顾无论水有多深山有多陡\n" +
                "逆境中站起 走过谦卑的路途\n" +
                "热情被唤起 铭记先辈的付出\n" +
                "不要冷漠着 肩并肩负重前行\n" +
                "不必沉默了\n" +
                "放声歌唱把你我的脊梁坚挺\n" +
                "经过了多年的磨炼 告别了昨天\n" +
                "挣开了锁链 凝聚了火焰\n" +
                "钢筋和铁骨刷新着纪录\n" +
                "勤劳和善良是我为人的艺术\n" +
                "冰天雪地里练就了一身本领\n" +
                "把不可能的变成了可能\n" +
                "推开世界的门 只要每个人\n" +
                "肩负使命担当着各自的责任\n" +
                "乘风破浪就像踏步在长征的路上\n" +
                "誓言捍卫这片大地利剑般的目光\n" +
                "我为我的祖国而感到骄傲\n" +
                "就让五星红旗自由地飘\n" +
                "有一种力量无人能抵挡\n" +
                "它永不言败生来倔强\n" +
                "有一种理想照亮了迷茫\n" +
                "在那写满荣耀的地方\n" +
                "有一种力量无人能抵挡\n" +
                "它永不言败生来倔强\n" +
                "有一种理想照亮了迷茫\n" +
                "在我深感自豪的地方\n" +
                "有一种力量无人能抵挡\n" +
                "它永不言败生来倔强\n" +
                "有一种理想照亮了迷茫\n" +
                "在那写满荣耀的地方");
        list.add(cell6);
        AnimListCell cell7 = new AnimListCell();
        cell7.setStrTime("The Man Who Sold The World");
        cell7.setStrContent("David Bowie");
        cell7.setStrText("We passed upon the stair, we spoke of was and when\n" +
                "Although I wasn't there, he said I was his friend\n" +
                "Which came as some surprise I spoke into his eyes\n" +
                "I thought you died alone, a long long time ago\n" +
                "Oh no, not me\n" +
                "I never lost control\n" +
                "You're face to face\n" +
                "With the Man Who Sold The World\n" +
                "I laughed and shook his hand, and made my way back home\n" +
                "I searched for form and land, for years & years I roamed\n" +
                "I gazed a gazely stare at all the millions here\n" +
                "We must have died alone, a long long time ago\n" +
                "Who knows? not me\n" +
                "We never lost control\n" +
                "You're face to face\n" +
                "With the Man Who Sold The World.\n" +
                "Who knows? not me\n" +
                "We never lost control\n" +
                "You're face to face\n" +
                "With the Man Who Sold The World.");
        list.add(cell7);
    }

    /**
     * 设置选中框的位置
     * @param i 0~3
     */
    private void setSelectViewPosition(int i){
        iOffSite = i;
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ll_mask.getLayoutParams());
        if (list.size() <= 1){
            params.setMargins(0,0,0,0);
        } else {
            params.setMargins(params.leftMargin, (int) getResources().getDimension(R.dimen.list_cell_height) * i,params.rightMargin,params.bottomMargin);
        }
        ll_mask.setLayoutParams(params);
    }

    private void refreshActivity(){
        if (list.size() <= 1){
            setSelectViewPosition(0);
        } else {
            setSelectViewPosition(1);
        }
        adapter.notifyDataSetChanged();
    }

    //设置底部的text
    private void setButtomText(String strText){
        tv_more_text.setText(strText);
    }

    @Override
    protected void onResume() {
        super.onResume();

        list = new ArrayList<>();
        adapter = new ItemAnimListCellAdapter(mContext,list);
        lv_list.setAdapter(adapter);

        initListData();
        refreshActivity();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (list.size()){
            case 0:{
                AnimListCell cell1 = new AnimListCell();
                cell1.setStrTime("10:30:25");
                cell1.setStrContent("yoo");
                cell1.setStrText("【三无】站在舞台旋转 在灯光中沉沦\n天鹅绒幔布背景做陪衬\n【双笙】以诙谐的语言嘲弄着我的可悲\n油彩覆盖我泪湿的笑纹\n" +
                        "【合】将浓妆艳抹灿艳假装做告慰\n在霓虹光点间流连的愚昧\n只是身在其中早已无所谓\n在这夜晚灯火迷离下沉醉\n来往穿梭无情的路人\n" +
                        "谁来拯救我的可悲？");
                list.add(cell1);
            }break;
            case 1:{
                AnimListCell cell2 = new AnimListCell();
                cell2.setStrTime("10:30:38");
                cell2.setStrContent("Won’t stop running.running");
                cell2.setStrText("Do you know what's worth fighting for \nWhen it's not worth dying for?\nDoes it take your breath away\nAnd you feel yourself suffocating?\n"+
                        "Does the pain weigh out the pride?\nAnd you look for a place to hide?\nDid someone break your heart inside?\nYou're in ruins");
                list.add(cell2);
            }break;
            case 2:{
                AnimListCell cell3 = new AnimListCell();
                cell3.setStrTime("10:31:06");
                cell3.setStrContent("Goodbye my friend its hard to die");
                cell3.setStrText("One' 21 guns\nLay down your arms\nOne' 21 guns\nThrow up your arms into the sky'\n"+
                        "You and I\nWhen you're at the end of the road\nAnd you lost all sense of control\n" +
                        "And your thoughts have taken their toll\\nWhen your mind breaks the spirit of your soul\n" +
                        "Your faith walks on broken glass\nAnd the hangover doesn't pass\nNothing's ever built to last");
                list.add(cell3);
            }break;
            case 3:{
                AnimListCell cell4 = new AnimListCell();
                cell4.setStrTime("10:31:42");
                cell4.setStrContent("when all the birds are singing in the sky");
                cell4.setStrText("我竟然还记得几年前无聊背过的平假名\n" +
                        "\n" +
                        "但是并没有什么用\n" +
                        "\n" +
                        "能读出来不知道什么意思\n" +
                        "\n" +
                        "ひ吐着舌头笑的hihi\n" +
                        "\n" +
                        "や剪小\uD83D\uDC14\uD83D\uDC14发出ya~的叫声\n" +
                        "\n" +
                        "く横着看是哭ku的嘴型\n" +
                        "\n" +
                        "む停留一只乌鸦的十字架下躺着一个人的坟墓mu");
                list.add(cell4);
            }break;
            case 4:{
                AnimListCell cell5 = new AnimListCell();
                cell5.setStrTime("10:32:03");
                cell5.setStrContent("Think of me and I'll be there");
                cell5.setStrText("点开同学的推送。。啊\n" +
                        "\n" +
                        "后面那座楼，就是中传举办动画系毕业展的地方\n" +
                        "\n" +
                        "虽然我也只是去年去了一次吧。。");
                list.add(cell5);
            }break;
            case 5:{
                AnimListCell cell6 = new AnimListCell();
                cell6.setStrTime("10:32:47");
                cell6.setStrContent("We had joy. We had fun.");
                cell6.setStrText("-Aura- 更新了一个连载阶段\n" +
                        "8个小时前\n" +
                        "冗繁削尽留清瘦\n" +
                        "\n" +
                        "2018.05.16\n" +
                        "\n" +
                        "雷暴雨使我又快活又兴奋，\n" +
                        "\n" +
                        "停电却使我一秒丧失斗志。");
                list.add(cell6);
            }break;
            case 6:{
                AnimListCell cell7 = new AnimListCell();
                cell7.setStrTime("10:32:47");
                cell7.setStrContent("We had seasons in the sun");
                cell7.setStrText("最后一条，手写两行\n第二行\n添加到第七条的时候，会清空listView");
                list.add(cell7);
            }break;
//                    case 7:{
//                        AnimListCell cell1 = new AnimListCell();
//                        cell1.setStrTime("10:30:25");
//                        cell1.setStrContent("77777777");
//                        list.add(cell1);
//                    }break;
            default:{
                list.clear();
            }break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        hasScroll = false;
        setButtomText(list.get(i).getStrText());
        if (i > 0) {
            lv_list.setSelection(i - 1);
            if (i == list.size() - 2){
                setSelectViewPosition(2);
            } else if (i == list.size() - 1) {
                setSelectViewPosition(3);
            } else {
                setSelectViewPosition(1);
            }
        } else {
            setSelectViewPosition(0);
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int iScrollState) {
        // 1 滚动  0停止滚动
        if (iScrollState==SCROLL_STATE_IDLE){
            hasScroll = true;
            int iFirstVisiblePosition = lv_list.getFirstVisiblePosition();
            Log.i("Now",iFirstVisiblePosition + "");
            //TODO 判断当前显示的数据，哪个是被选中的，然后显示出其strText
            setButtomText(list.get(iFirstVisiblePosition+iOffSite).getStrText());
        }
    }

    @Override
    public void onScroll(AbsListView absListView, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        Log.i("ON SCROLL",firstVisibleItem + " " + visibleItemCount + " " + " " + totalItemCount + " ");
        if (null == list){
            return;
        }
        if (hasScroll) {
            setButtomText(list.get(firstVisibleItem + iOffSite).getStrText());
            hasScroll = false;
        }
    }
}


