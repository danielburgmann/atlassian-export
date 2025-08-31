# Open TODOs

## General

* replace html treated as string replacement by DOM based approach
  * image src
  * hrefs
  * entities (0x00A0 to `&nbsp;`)
* add disk based cache for large exports to lower memory consumption

## Confluence export

* implement dryRun Option for confluence export
* use [CQL](https://developer.atlassian.com/server/confluence/advanced-searching-using-cql/) to select export pages

## JIRA export

* replace usage of [deprecated search API](https://developer.atlassian.com/changelog/?subid=1750765129#CHANGE-2046) in `de.smarthelios.jira.io.JiraClient` by [current API](https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issue-search/#api-rest-api-3-search-jql-get)
* style issue.html
* make detail fields for issue export configurable (esp. custom fields)
* introduce better view model for issue.html to extract logic from template
