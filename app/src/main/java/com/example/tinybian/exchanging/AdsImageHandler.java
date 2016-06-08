package com.example.tinybian.exchanging;


import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by tinybian on 2015/11/29.
 */
public class AdsImageHandler extends Handler {
    ClassificationPagerAdapter parentAdapter;
    /*
     * 请求更新显示的View。
     */
    protected static final int MSG_UPDATE_IMAGE  = 1;
    /*
     * 请求暂停轮播。
     */
    protected static final int MSG_KEEP_SILENT   = 2;
    /*
     * 请求恢复轮播。
     */
    protected static final int MSG_BREAK_SILENT  = 3;
    /*
     * 记录最新的页号，当用户手动滑动时需要记录新页号，否则会使轮播的页面出错。
     * 例如当前如果在第一页，本来准备播放的是第二页，而这时候用户滑动到了末页，
     * 则应该播放的是第一页，如果继续按照原来的第二页播放，则逻辑上有问题。
     */
    protected static final int MSG_PAGE_CHANGED  = 4;

    //轮播间隔时间
    protected static final long MSG_DELAY = 3000;
    //当前页号
    private int currentItem;

    protected AdsImageHandler(ClassificationPagerAdapter parentAdapter, int currentItem){
        this.parentAdapter = parentAdapter;
        this.currentItem = currentItem;
    }

    @Override
    public void handleMessage(Message msg) {

        Log.d("handleMessage", "Message:"+msg.toString());

        if(parentAdapter == null)//如果该页面已经回收，就不用处理信息了。
            return;

        //检查消息队列并移除未发送的消息，这主要是避免在复杂环境下消息出现重复等问题。
        if (parentAdapter.adsImageHandler.hasMessages(MSG_UPDATE_IMAGE)){
            parentAdapter.adsImageHandler.removeMessages(MSG_UPDATE_IMAGE);
            Log.d("removeMsg", "executed");
        }

        switch (msg.what)
        {
            case MSG_UPDATE_IMAGE:
                currentItem++;
                parentAdapter.holderAdsImage.vp.setCurrentItem(currentItem);
                Log.d("MSG_UPDATE_IMAGE", Integer.toString(currentItem));
                //parentAdapter.notifyDataSetChanged();
                //延时后进行下次播放
                boolean res = this.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_BREAK_SILENT:
                parentAdapter.adsImageHandler.sendEmptyMessageDelayed(MSG_UPDATE_IMAGE, MSG_DELAY);
                break;
            case MSG_KEEP_SILENT:
                break;
            case MSG_PAGE_CHANGED:
                //用户手动滑动时，记录当前页号，避免下次滑动页号混乱
                currentItem = msg.arg1;
                break;
            default:
                break;
        }
    }
}
