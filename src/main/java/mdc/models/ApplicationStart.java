package mdc.models;

import engine.ScoreKeeper;
import mdc.engine.GameEngine;
import mdc.listeners.KeysListener;
import mdc.listeners.MousesListener;
import mdc.screenpainters.AboutScreen;
import mdc.screenpainters.GameScreen;
import mdc.screenpainters.MenuScreen;
import mdc.screenpainters.ScoreScreen;
import mdc.states.about.MDCAbout;
import mdc.states.game.MDCGame;
import mdc.states.menu.MDCMenu;
import mdc.states.score.MDCScore;
import mdc.tools.Config;
import mdc.tools.ConfigLoader;
import mdc.tools.GraphPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * Initialize the game window and start the game
 */
public class ApplicationStart {
    public static void main(String[] args) throws IOException {
        Config config = ConfigLoader.loadConfig("src/main/resources/config/config.json");
        assert config != null;


        JFrame window = new JFrame("Monopoly Deal Cards");
        window.setIconImage(GraphPainter.getImage(config.getImagePath().getIcon()));

        KeysListener keysListener = new KeysListener();
        MousesListener mousesListener = new MousesListener();

        // 添加监听器
        window.setFocusable(true);
        window.addKeyListener(keysListener);
        window.addMouseListener(mousesListener);
        window.addMouseMotionListener(mousesListener);

        // 初始化窗口和引擎
        MDCGame game = new MDCGame(keysListener, mousesListener);
        MDCMenu menu = new MDCMenu(keysListener, mousesListener);
        MDCAbout about = new MDCAbout(keysListener);
        MDCScore score = new MDCScore(keysListener);
        MenuScreen menuScreen = new MenuScreen();
        AboutScreen aboutScreen = new AboutScreen();
        ScoreKeeper scoreKeeper = new ScoreKeeper("src/main/resources/text/scores.txt");
        ScoreScreen scoreScreen = new ScoreScreen(scoreKeeper);
        GameScreen gameScreen = new GameScreen(game);
        GameEngine engine = new GameEngine(game, menu, about, score, window, menuScreen, aboutScreen, scoreScreen, gameScreen, scoreKeeper);

//        window.setUndecorated(true); // 自定义边框
//        JPanel titleBar = new JPanel(); // 正上方标题框
//        titleBar.setLayout(new BorderLayout());
//        titleBar.setPreferredSize(new Dimension(game.getScreenWidth(), 20)); // 设定标题栏的宽度和高度
//        titleBar.setBackground(Color.LIGHT_GRAY); // 设定标题栏的背景颜色
//
//        window.getRootPane().setBorder(BorderFactory.createMatteBorder(0, 1, 1, 1, Color.LIGHT_GRAY));
//
//
//
//        // 设置标题
//        JLabel titleLabel = new JLabel("Monopoly Deal Cards");
//        titleLabel.setFont(new Font("Courier New", Font.BOLD, 16));
//        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0)); // 顶部、左侧、底部、右侧的边距
//        // 设置关闭按钮
//        JButton closeButton = new JButton("X");
//        closeButton.setFont(new Font("Courier New", Font.BOLD, 16));
//        closeButton.setBackground(new Color(255, 65, 65));
//        closeButton.addActionListener(e -> engine.exitGame()); // 为关闭按钮添加一个事件监听器来关闭窗口
//
//
//        titleBar.add(titleLabel, BorderLayout.WEST);
//        titleBar.add(closeButton, BorderLayout.EAST);
//        window.add(titleBar, BorderLayout.NORTH); // 将标题栏添加到窗口的北部（顶部）
//
//        window.setSize(MDCGame.SCREEN_WIDTH + 2, MDCGame.SCREEN_HEIGHT + 21);

        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // 先取消掉关闭键直接退出程序的功能
        window.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                engine.exitGame();
            }
        });
        Insets insets = window.getInsets();
        window.setSize(MDCGame.SCREEN_WIDTH + insets.left + insets.right,
                MDCGame.SCREEN_HEIGHT + insets.top + insets.bottom);
        window.setLocationRelativeTo(null);
        window.setResizable(false);

        engine.run();
    }
}