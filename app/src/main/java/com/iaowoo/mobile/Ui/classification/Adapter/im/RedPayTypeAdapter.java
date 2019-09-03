package com.iaowoo.mobile.Ui.classification.Adapter.im;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.iaowoo.mobile.R;
import com.iaowoo.mobile.Ui.classification.Model.im.RedPayType;
import java.util.List;

public class RedPayTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<RedPayType> data;

    public RedPayTypeAdapter(Context context, List<RedPayType> data) {
        mContext = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.dialog_item_pay_type, null);
            holder = new ViewHolder();
            holder.tvName = (TextView) convertView.findViewById(R.id.tv_pay_name);
            holder.tvBalance = (TextView) convertView.findViewById(R.id.tv_pay_balance);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        RedPayType redPayType = data.get(position);

        if (!TextUtils.isEmpty(redPayType.getPayName())) {
            holder.tvName.setText(redPayType.getPayName());
        }

        if (!TextUtils.isEmpty(redPayType.getBalance())) {
            String str = mContext.getResources().getString(R.string.balance_amount);
            holder.tvBalance.setText(String.format(str, redPayType.getBalance()));
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvName;
        TextView tvBalance;
    }
}
