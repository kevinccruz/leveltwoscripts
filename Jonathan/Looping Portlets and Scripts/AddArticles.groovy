import com.liferay.portlet.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextThreadLocal;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.util.WebKeys;

ServiceContext serviceContext =
	ServiceContextThreadLocal.getServiceContext();

ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest.getAttribute(
	WebKeys.THEME_DISPLAY);

long groupId = themeDisplay.getCompanyGroupId();

long userId = themeDisplay.getUserId();

User user = UserLocalServiceUtil.getUserById(userId);

Locale locale = user.getLocale();

String defaultArticleTitle = "article";

Map<Locale, String> titleMap = new HashMap<Locale, String>();

for (int i = 0; i <=1000; i++) {
	String content = "<?xml version=\"1.0\"?><root available-locales=\"en_US\" default-locale=\"en_US\"><static-content language-id=\"en_US\"><![CDATA[" + defaultArticleTitle + String.valueOf(i) + "]]></static-content></root>";

	titleMap.put(locale, defaultArticleTitle + String.valueOf(i));

	JournalArticleLocalServiceUtil.addArticle(
		userId, //userId
		groupId, //groupId
		0L, //folderId
		titleMap, //titleMap
		null, //descriptionMap
		content, //content
		null, //ddmStructureKey
		null, //ddmTemplateKey
		serviceContext //serviceContext
	);

	titleMap.clear();
}