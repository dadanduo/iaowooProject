package com.iaowoo.mobile.im;

import java.util.List;
import java.util.ListIterator;

import io.rong.imkit.DefaultExtensionModule;
import io.rong.imkit.RongExtension;
import io.rong.imkit.emoticon.IEmoticonTab;
import io.rong.imkit.plugin.IPluginModule;
import io.rong.imkit.widget.provider.FilePlugin;
import io.rong.imlib.model.Conversation;

public class MyExtensionModule extends DefaultExtensionModule {
    @Override
    public List<IPluginModule> getPluginModules(Conversation.ConversationType conversationType) {
        List<IPluginModule> pluginModules = super.getPluginModules(conversationType);
        ListIterator<IPluginModule> iterator = pluginModules.listIterator();
        while (iterator.hasNext()) {
            IPluginModule integer = iterator.next();
            if (integer instanceof FilePlugin) {
                iterator.remove();
            }
        }
        pluginModules.add(new RedPacketPlugin());
        return pluginModules;
    }

    @Override
    public List<IEmoticonTab> getEmoticonTabs() {
        List<IEmoticonTab> emoticonTabs =  super.getEmoticonTabs();
        return emoticonTabs;
    }

    @Override
    public void onAttachedToExtension(RongExtension extension) {
        super.onAttachedToExtension(extension);
    }

    @Override
    public void onDetachedFromExtension() {
        super.onDetachedFromExtension();
    }
}
