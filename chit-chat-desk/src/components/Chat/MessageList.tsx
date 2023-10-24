'use client'
/**
 * <p>
 * 聊天列表
 * </p>
 *
 * @version: v1.0
 * @author: Clover
 * @create: 2023-10-23 14:30
 */
import React, { useCallback, useState } from 'react'
import { ChatListItem } from '../Chat/ChatListItem'

const list = [
  {
    id: '1',
    name: 'Clover',
    lastMessage: 'Hello',
    lastMessageTime: '2023-10-23 14:30',
    unread: 1,
  },
  {
    id: '2',
    name: 'Bella',
    lastMessage: 'Hi',
    lastMessageTime: '2023-10-23 14:31',
    unread: 0,
  },
  {
    id: '3',
    name: 'Charlie',
    lastMessage: 'How are you?',
    lastMessageTime: '2023-10-23 14:32',
    unread: 0,
  },
  // ...
  {
    id: '18',
    name: 'Alice',
    lastMessage: 'I am fine.',
    lastMessageTime: '2023-10-23 14:39',
    unread: 0,
  },
  {
    id: '19',
    name: 'David',
    lastMessage: 'Can you help me with my assignment?',
    lastMessageTime: '2023-10-23 14:40',
    unread: 1,
  },
  {
    id: '20',
    name: 'Eva',
    lastMessage: 'Sure, I can help you.',
    lastMessageTime: '2023-10-23 14:41',
    unread: 0,
  },
]

function ChatListRenderer(props: {
  selectId: string | undefined
  onClick: (item: any) => void
}) {
  return list.map((item) => (
    <ChatListItem
      key={item.id}
      title={item.name}
      date={item.lastMessageTime}
      summary={item.lastMessage}
      onClick={() => props.onClick(item)}
      selected={props.selectId === item.id}
    />
  ))
}

export function MessageList() {
  const [selectId, changeSelectId] = useState<string | undefined>(undefined)

  function onClickHandler(item: any) {
    changeSelectId(item.id)
  }

  return (
    <div className="chat-list">
      <div className="chat-list-item">
        <ChatListRenderer
          selectId={selectId}
          onClick={onClickHandler}
        />
      </div>
    </div>
  )
}
