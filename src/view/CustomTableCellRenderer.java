package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class CustomTableCellRenderer extends DefaultTableCellRenderer {

    private Font font = new Font("MV Boli", Font.BOLD, 15);
    private Color foregroundColor = Color.BLACK;

    public CustomTableCellRenderer(Font font, Color foregroundColor) {
        super();
        this.font = font;
        this.foregroundColor = foregroundColor;
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cellComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        cellComponent.setFont(font);
        cellComponent.setForeground(foregroundColor);

        return cellComponent;
    }
}
