package de.smarthelios.jira.export.model

import groovy.util.logging.Slf4j

import static de.smarthelios.atlassian.export.convert.Convert.toDate

/**
 * Model representing sprint information attached to an issue.
 */
@Slf4j
class Sprint {

    String id
    String name
    String state
    Date startDate
    Date endDate
    Date completeDate
    String rapidViewId // used in server
    String boardId // used in cloud
    String goal

    // see https://community.atlassian.com/t5/Jira-questions/Sprint-field-value-REST-API/qaq-p/229495
    // seems like server is using square brackets while cloud uses curly brackets
    static Sprint fromUglySerialization(String serialized) {

        int startBracket = serialized.indexOf('[') > -1 ? serialized.indexOf('[') : serialized.indexOf('{')
        int endBracket = serialized.lastIndexOf(']') > -1 ? serialized.lastIndexOf(']') : serialized.lastIndexOf('}')
        if(startBracket > -1 && endBracket > -1 && endBracket > startBracket) {
            String data = serialized.substring(
                    startBracket + 1,
                    endBracket
            )
            def sprint = data.split(',').collectEntries { it.split('=') }

            return new Sprint(
                    id: sprint.id,
                    name: sprint.name,
                    state: sprint.state,
                    rapidViewId: sprint?.rapidViewId ?: null,
                    boardId: sprint?.boardId ?: null,
                    startDate: toDate(sprint.startDate),
                    endDate: toDate(sprint.endDate),
                    completeDate: toDate(sprint.completeDate),
                    goal: sprint.goal
            )
        }
        else {
            log.debug 'Could not parse ugly sprint data serialization: "{}"', serialized
            return null
        }
    }

}
