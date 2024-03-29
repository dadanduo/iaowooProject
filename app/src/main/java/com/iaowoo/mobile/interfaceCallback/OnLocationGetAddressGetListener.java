/**  
 * Project Name:Android_Car_Example  
 * File Name:OnLocationGetListener.java  
 * Package Name:com.amap.api.car.example  
 * Date:2015年4月2日下午6:17:17  
 *  
*/  
  
package com.iaowoo.mobile.interfaceCallback;

import com.iaowoo.mobile.ScottMap.PositionEntity;

/**
 * ClassName:OnLocationGetListener <br/>  
 * Function: 逆地理编码或者定位完成后回调接口<br/>  
 * Date:     2015年4月2日 下午6:17:17 <br/>  
 * @author   yiyi.qi  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public interface OnLocationGetAddressGetListener {

	public void onLocationAddressGet(PositionEntity entity);
	
	public void onRegeAddresscodeGet(PositionEntity entity);
}
  
