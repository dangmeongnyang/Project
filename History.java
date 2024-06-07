package health;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

public class History extends JDialog {
    private JTable table;
    private DefaultTableModel model;

    public History(JFrame parent) {
    	super(parent, "History", true);
        setSize(400, 300);
        setLocationRelativeTo(parent);

        // 테이블 모델 생성
        model = new DefaultTableModel();
        model.addColumn("Date");
        model.addColumn("Calories");
        model.addColumn("Exercise");

        // 테이블 생성
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 데이터 로드 및 테이블 업데이트
        loadData();
    }

    private void loadData() {
        // dataMap을 날짜 순으로 정렬
        Map<String, Server.DailyData> sortedMap = new TreeMap<>(Server.dataMap);

        // 정렬된 맵에서 데이터 추출 및 테이블 모델에 추가
        for (Map.Entry<String, Server.DailyData> entry : sortedMap.entrySet()) {
            String date = entry.getKey();
            Server.DailyData data = entry.getValue();
            model.addRow(new Object[]{date, data.getCalories(), data.getExercise()});
        }
    }
}