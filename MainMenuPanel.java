package health;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuPanel extends JPanel {
    private JLabel userInfoLabel;
    private User user;
    private MainFrame mainFrame;

    public MainMenuPanel(User user) {
        this.user = user;
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // 왼쪽 메뉴 버튼 패널
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(4, 1, 5, 5));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JButton noticeButton = new JButton("Notice");
        JButton todayTotalButton = new JButton("Today_total");
        JButton historyButton = new JButton("History");
        JButton qnaButton = new JButton("Q&A");

        menuPanel.add(noticeButton);
        menuPanel.add(todayTotalButton);
        menuPanel.add(historyButton);
        menuPanel.add(qnaButton);

        add(menuPanel, BorderLayout.WEST);

        // 중앙 사용자 정보 패널
        JPanel userInfoPanel = new JPanel(new GridBagLayout());
        userInfoPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5);

        userInfoLabel = new JLabel(getUserInfoText());
        userInfoPanel.add(userInfoLabel, gbc);

        add(userInfoPanel, BorderLayout.CENTER);

        // Notice 버튼에 액션 리스너 추가
        noticeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NoticeDialog.showNoticeDialog();
            }
        });

        // TodayTotal 버튼에 액션 리스너 추가
        todayTotalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TodayTotal todayTotalDialog = new TodayTotal(mainFrame);
                todayTotalDialog.setVisible(true);
            }
        });

        // History 버튼에 액션 리스너 추가
        historyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                History historyDialog = new History(mainFrame);
                historyDialog.setVisible(true);
            }
        });
        
        qnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                QnA qnaDialog = new QnA(mainFrame);
                qnaDialog.setVisible(true);
            }
        });
    }

    private String getUserInfoText() {
        LocalDate currentDate = LocalDate.now(); // 현재 날짜 가져오기
        String dateString = currentDate.toString(); // 날짜를 문자열로 변환
        
        if (user != null) {
            return "<html><pre>Name: " + user.getName() + "<br>ID: " + user.getUsername() +
                    "<br>Phone Number: " + user.getPhoneNumber() +
                    "<br>Date: " + dateString + "</pre></html>";
        } else {
            return "<html><pre>User information will be displayed here.<br>Date: " + dateString + "</pre></html>";
        }
    }
}