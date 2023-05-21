package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScrollableComboBoxExample {
    static class ScrollableComboBox<E> extends JPanel {
        private JButton button;
        private JPopupMenu popupMenu;
        private JScrollPane scrollPane;
        private JList<E> list;

        public ScrollableComboBox(E[] items) {
            setLayout(new BorderLayout());

            button = new JButton();
            button.setPreferredSize(new Dimension(180, 40)); // Imposta le dimensioni desiderate
            button.setText("Scelte:");
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.setFont(new Font("MV Boli", Font.BOLD, 17));
            button.setBorder(null);

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    popupMenu.show(button, 31,button.getHeight());
                }
            });

            list = new JList<>(items);
            scrollPane = new JScrollPane(list);
            scrollPane.setSize(120,40);

            popupMenu = new JPopupMenu();
            popupMenu.add(scrollPane);

            add(button);

        }

        public E getSelectedItem() {
            return list.getSelectedValue();
        }

        public JScrollPane getScrollPane() {
            return scrollPane;
        }

        public void setScrollPane(JScrollPane scrollPane) {
            this.scrollPane = scrollPane;
        }

        public JButton getButton() {
            return button;
        }

        public void setButton(JButton button) {
            this.button = button;
        }

        public JPopupMenu getPopupMenu() {
            return popupMenu;
        }

        public void setPopupMenu(JPopupMenu popupMenu) {
            this.popupMenu = popupMenu;
        }
    }
}
