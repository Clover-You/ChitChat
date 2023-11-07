/**
 * <p>
 * 首页
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 14:09
 */
import { redirect } from 'next/navigation'

export default function Home() {
  // 直接把路由转到聊天窗口
  redirect('/chat')
}
