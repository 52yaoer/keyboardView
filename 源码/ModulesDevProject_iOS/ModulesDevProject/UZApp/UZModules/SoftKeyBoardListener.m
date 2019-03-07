//
//  EaseMobData.m
//  UZApp
//
//  Created by 赵飞 on 16/8/2.
//  Copyright © 2016年 APICloud. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "SoftKeyBoardListener.h"

@interface SoftKeyBoardListener ()
{
    
}
@end

@implementation SoftKeyBoardListener
    
static SoftKeyBoardListener *instance = nil;

+ (SoftKeyBoardListener *) obtain {
    @synchronized(self) {
        if (instance == nil) {
            instance = [[SoftKeyBoardListener alloc] init];
        }
    }
    return instance;
}

- (id)init {
    if (self = [super init]) {
        
    }
    return self;
}

-(void)setListener:(BOOL)show{
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillShowNotification object:nil];
    [[NSNotificationCenter defaultCenter] removeObserver:self name:UIKeyboardWillHideNotification object:nil];
    
    if(show){
        //监听当键盘将要出现时
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillShow:)  name:UIKeyboardWillShowNotification  object:nil];
        //监听当键将要退出时
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(keyboardWillHide:)  name:UIKeyboardWillHideNotification object:nil];
    }
}

//当键盘出现
- (void)keyboardWillShow:(NSNotification *)notification
{
    NSDictionary *keyBordInfo = [notification userInfo];
    NSValue *value = [keyBordInfo objectForKey:UIKeyboardFrameEndUserInfoKey];
    CGRect keyBoardRect = [value CGRectValue];
    float height = keyBoardRect.size.height;
    CGRect beginRect = [[keyBordInfo objectForKey:UIKeyboardFrameBeginUserInfoKey] CGRectValue];
    CGRect endRect = [[keyBordInfo objectForKey:UIKeyboardFrameEndUserInfoKey] CGRectValue];
    // 第三方键盘回调三次问题，监听仅执行最后一次
    if(beginRect.size.height > 0 && (beginRect.origin.y - endRect.origin.y > 0)){
        if(_delegate && [_delegate respondsToSelector:@selector(keyBoardShow:)]){
            [_delegate keyBoardShow:height];
        }
    }
}

//当键退出
- (void)keyboardWillHide:(NSNotification *)notification
{
    if(_delegate && [_delegate respondsToSelector:@selector(keyBoardHide:)]){
        [_delegate keyBoardHide:0];
    }
}

@end;
