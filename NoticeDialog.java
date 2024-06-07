package health;

import javax.swing.*;
import java.awt.*;

public class NoticeDialog {
    public static void showNoticeDialog() {
        // 새로운 JFrame 생성
        JFrame noticeFrame = new JFrame("Notice");
        noticeFrame.setSize(500, 400);
        noticeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 텍스트 내용을 담은 JLabel 생성
        JLabel noticeLabel = new JLabel("<html><pre>안녕하세요? 이 메뉴는 사용방법 및 주의사항에 관한 글을 담고 있습니다.\n"
        		+ "\n이 프로그램은 고객님이 운동을 하시는데 있어서 쉽게 관리하고 기록하기 위한 목적으로 만들어졌습니다.\n"
        		+ "\n먼저 Total_today는 고객님이 오늘 한 활동에 대해 기록하는 부분입니다.\n"
        		+ "오늘 무엇을 드셨고, 어떠한 활동을 하였는지를 기록하고 필요하다면 사진도 추가를 하실 수 있습니다.\n "
        		+ "\n다음으로 History는 고객님이 Total_today에서 기록하신 내용을 확인하실 수 있는 메뉴입니다.\n"
        		+ "\n마지막으로 Q&A는 고객님이 이 프로그램을 사용하시면서 문의사항이나, 요구사항등을 기재하시는 곳입니다.\n"
        		+ "올려주시면 순차적으로 성실하게 답변하겠습니다!\n"
        		+ "\n운동은 노력하는만큼 정직한 결과가 따라오기 마련입니다. 열심히 임하셔서 원하는 목표 이루셨으면 합니다!</pre></html>");     
        
        noticeLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // 프레임에 JLabel 추가
        noticeFrame.add(noticeLabel);

        // 프레임을 중앙에 위치시키고 표시
        noticeFrame.setLocationRelativeTo(null);
        noticeFrame.setVisible(true);
    }
}
