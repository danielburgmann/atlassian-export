package de.smarthelios.confluence.io

import de.smarthelios.atlassian.export.model.ImageExportReplacement
import de.smarthelios.atlassian.io.Gif
import de.smarthelios.atlassian.io.Resource
import groovy.util.logging.Slf4j

import java.util.regex.Pattern

@Slf4j
final class ImageReplacements {

    public static final ImageExportReplacement TRANSPARENT_PIXEL = new ImageExportReplacement(
            filename: Gif.TRANSPARENT.filename,
            bytes: Gif.TRANSPARENT.bytes
    )

    public static final ImageExportReplacement USER_ICON = new ImageExportReplacement(
            filename: 'user-icon.svg',
            bytes: Resource.confluenceExport('/static/user-icon.svg').bytes
    )

    static final Pattern attachmentAvatarImgSrc = ~'^/download/attachments/\\d+/user-avatar$'

    static ImageExportReplacement get(String imgSrc) {
        ImageExportReplacement replacement
        if(ConfluenceClient.isJiraConfluenceMacroImgSrc(imgSrc)) {
            log.info 'Will replace JIRA macro image "{}" by transparent pixel gif', imgSrc
            replacement = TRANSPARENT_PIXEL
        }
        else if(isAvatar(imgSrc)) {
            log.info('Will replace avatar image "{}" by default user icon.', imgSrc)
            replacement = USER_ICON
        }
        else {
            replacement = null
        }

        return replacement
    }

    static boolean exists(String imgSrc) {
        null != get(imgSrc)
    }

    static boolean isAvatar(String imgSrc) {
        '/images/icons/profilepics/default.svg' == imgSrc || attachmentAvatarImgSrc.matcher(imgSrc).matches()
    }

}
