/*
 * FilterContinuousPanel.java
 *
 * Copyright (c) 2002-2015 Alexei Drummond, Andrew Rambaut and Marc Suchard
 *
 * This file is part of BEAST.
 * See the NOTICE file distributed with this work for additional
 * information regarding copyright ownership and licensing.
 *
 * BEAST is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 *  BEAST is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with BEAST; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA  02110-1301  USA
 */

package tracer.traces;

import javax.swing.*;
import java.awt.*;
import java.util.TreeSet;

/**
 * @author Walter Xie
 */
public class FilterContinuousPanel extends FilterAbstractPanel {
    JTextField minField;
    JTextField maxField;
    double lower;
    double upper;

    FilterContinuousPanel(double[] minMax, String[] bound) {
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        c.weightx = 5;
//        c.weighty = 10;
//        c.gridx = 0;
        c.gridy = 0;
        c.insets = new Insets(20, 10, 0, 10);
        c.anchor = GridBagConstraints.FIRST_LINE_START;

        if (minMax[0] >= minMax[1]) {
            // invalid limits in original log, such as incorrectly logged fixed parameter
            add(new JLabel("Invalid values : "));

            c.gridy = 1;
            add(new JLabel("Lower = " + minMax[0] + ", upper = " + minMax[0] + " !"));

        } else {

            if (bound == null) {
                bound = new String[2];
            }
            lower=minMax[0];
            upper=minMax[1];

            minField = new JTextField(bound[0]);
            minField.setColumns(20);

            //c.gridy = 0;
            add(new JLabel("Lower to rescale parameter : "), c);

            c.gridy = 1;
            add(minField, c);

            c.gridy = 2;
            add(new JLabel("which should >= " + lower), c);

            maxField = new JTextField(bound[1]);
            maxField.setColumns(20);

            c.gridy = 3;
            c.insets = new Insets(50, 10, 0, 10);
            add(new JLabel("Upper to rescale parameter : "), c);

            c.gridy = 4;
            c.insets = new Insets(20, 10, 0, 10);
            add(maxField, c);

            c.gridy = 5;
            add(new JLabel("which should <= " + upper), c);
        }
    }

    // return null, if text fields are not created,
    // or auto set min/max to lower/upper
    public String[] getSelectedValues() {
        if (minField==null || maxField==null)
            return null;
        if (minField.getText().equals("") && !maxField.getText().equals(""))
            minField.setText(Double.toString(lower));
        if (!minField.getText().equals("") && maxField.getText().equals(""))
            maxField.setText(Double.toString(upper));
        return new String[]{minField.getText(), maxField.getText()};
    }

}
