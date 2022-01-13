package de.smarthelios.atlassian.export.filter

import org.apache.commons.lang3.StringUtils

class EntityFilter implements HtmlFilter {

    static final EntityFilter instance = new EntityFilter()

    @Override
    String apply(String pageHtml) {
        StringUtils.replaceEach(pageHtml, ['\u00A0'] as String[], ['&nbsp;'] as String[])
    }
}
