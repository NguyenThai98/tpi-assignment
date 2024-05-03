package com.tpi.currency.Utils;

import com.tpi.currency.utils.DateUtils;
import org.junit.jupiter.api.Test;

/**
 * @created 03/05/2024 - 09:48
 * @project currency
 * @author: thainguyen
 */
public class DateUtilsTest {

    @Test
    public void Test_date_format_mmmm_d_yyyy_string() {
        DateUtils.convertCoinDeskFormatTime("February 1, 2024 02:46:38 UTC",DateUtils.COINDESK_FORMAT_TIME);
    }

    @Test
    public void Test_date_format_mmmm_dd_yyyy_string() {
        DateUtils.convertCoinDeskFormatTime("May 3, 2024 02:46:38 UTC",DateUtils.COINDESK_FORMAT_TIME);
    }


}
